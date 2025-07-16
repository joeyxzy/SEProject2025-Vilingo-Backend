package com.Vilingo.service;

import com.Vilingo.dto.ExerciseInfoResponse;
import com.Vilingo.dto.ExerciseItemResponse;
import com.Vilingo.dto.SectionInfoResponse;
import com.Vilingo.dto.VideoExerciseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 负责处理所有与“内容”相关的业务逻辑。
 * 它作为一个协调者，从静态仓库获取数据，并调用OSS服务生成动态URL。
 */
@Service
@RequiredArgsConstructor
public class ContentService {

    private final StaticContentService staticContentService;
    private final OssService ossService;

    /**
     * 获取完整的、本地化的章节列表。
     * @param lang 语言参数 (当前版本中未使用，为未来扩展保留)
     * @return 包含所有章节信息的列表
     */
    public List<SectionInfoResponse> getSections(String lang) {
        // 直接从静态数据仓库获取预先定义好的课程结构
        if(lang.equals("en"))
            return staticContentService.getEnFullContent();
        else
            return null;
    }

    /**
     * 根据ID获取单个练习的详细信息。
     * 此方法的核心任务是将存储在静态数据中的OSS Keys转换为可访问的预签名URL。
     *
     * @param id 练习的唯一ID
     * @return 包含完整URL的练习信息DTO，如果ID不存在则返回null
     */
    public ExerciseInfoResponse getExercise(Integer id) {
        // 1. 从静态数据仓库获取原始练习数据 (包含OSS Keys)
        ExerciseInfoResponse originalExercise = staticContentService.findExerciseById(id);

        // 如果根据ID找不到练习，返回null，由Controller处理为404 Not Found
        if (originalExercise == null) {
            return null;
        }

        // 2. 创建一个深拷贝，以避免在后续操作中修改被所有请求共享的静态缓存数据
        ExerciseInfoResponse exerciseWithUrls = deepCopy(originalExercise);

        // 3. 遍历练习中的所有项目，处理需要生成URL的项
        exerciseWithUrls.getItems().forEach(item -> {
            // 判断当前项是否为视频练习项
            if (item instanceof VideoExerciseResponse videoItem) {
                // 调用OssService为视频和字幕文件生成带签名的临时URL
                String videoUrl = ossService.generatePresignedUrl(videoItem.getVideoKey());
                String srtUrl = ossService.generatePresignedUrl(videoItem.getSrtKey());

                // 将生成的URL设置回DTO中，覆盖掉原有的(null)值
                videoItem.setVideo(videoUrl);
                videoItem.setSrt(srtUrl);
            }
            // 如果未来有其他需要处理的练习类型 (例如音频)，可以在这里添加 else if
        });

        // 4. 返回处理完成的、包含完整URL的练习信息
        return exerciseWithUrls;
    }

    /**
     * 执行深拷贝，创建一个与原始对象内容相同但完全独立的新对象。
     * 这是为了防止在多线程环境下修改作为单例存在的StaticContentService中的共享数据。
     * @param original 原始的练习信息对象
     * @return 一个全新的、内容相同的练习信息对象
     */
    private ExerciseInfoResponse deepCopy(ExerciseInfoResponse original) {
        ExerciseInfoResponse copy = new ExerciseInfoResponse();
        copy.setXp(original.getXp());
        copy.setDifficulty(original.getDifficulty());

        List<ExerciseItemResponse> copiedItems = original.getItems().stream()
                .map(item -> {
                    if (item instanceof VideoExerciseResponse videoItem) {
                        VideoExerciseResponse videoCopy = new VideoExerciseResponse();
                        videoCopy.setId(videoItem.getId());
                        // videoCopy.setType(videoItem.getType()); // <-- 移除这一行
                        videoCopy.setVideoKey(videoItem.getVideoKey());
                        videoCopy.setSrtKey(videoItem.getSrtKey());
                        return videoCopy;
                    }
                    return null;
                })
                .collect(Collectors.toList());

        copy.setItems(copiedItems);
        return copy;
    }
}