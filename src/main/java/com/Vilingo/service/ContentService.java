package com.Vilingo.service;

import com.Vilingo.dto.ExerciseInfoResponse;
import com.Vilingo.dto.ExerciseItemResponse;
import com.Vilingo.dto.SectionInfoResponse;
import com.Vilingo.dto.VideoExerciseResponse;
import com.Vilingo.dto.RetellingExerciseResponse;
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
    private final UserStateService userStateService;
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

    public ExerciseInfoResponse getExercise(Integer id) {
        ExerciseInfoResponse originalExercise = staticContentService.findExerciseById(id);

        if (originalExercise == null) {
            return null;
        }

        // 关键逻辑：判断是否是首次访问
        boolean firstVisit = userStateService.isFirstVisit(id);

        // 创建一个深拷贝，以避免修改静态缓存
        ExerciseInfoResponse exerciseForUser = deepCopy(originalExercise);

        // 根据访问状态，过滤出需要的练习项
        List<ExerciseItemResponse> filteredItems;
        if (firstVisit) {
            // 第一次访问：只保留 Video 类型的项
            filteredItems = exerciseForUser.getItems().stream()
                    .filter(item -> item instanceof VideoExerciseResponse)
                    .toList();
        } else {
            // 非第一次访问：只保留 Retelling 类型的项
            filteredItems = exerciseForUser.getItems().stream()
                    .filter(item -> item instanceof RetellingExerciseResponse)
                    .toList();
        }

        // 将过滤后的列表设置回 DTO
        exerciseForUser.setItems(filteredItems);

        // 遍历过滤后的列表，处理需要生成URL的项 (只有video项需要)
        exerciseForUser.getItems().forEach(item -> {
            if (item instanceof VideoExerciseResponse videoItem) {
                String videoUrl = ossService.generatePresignedUrl(videoItem.getVideoKey());
                String srtUrl = ossService.generatePresignedUrl(videoItem.getSrtKey());
                videoItem.setVideo(videoUrl);
                videoItem.setSrt(srtUrl);
            }
        });

        return exerciseForUser;
    }

    // deepCopy 方法需要能处理两种类型的 item
    private ExerciseInfoResponse deepCopy(ExerciseInfoResponse original) {
        ExerciseInfoResponse copy = new ExerciseInfoResponse();
        copy.setXp(original.getXp());
        copy.setDifficulty(original.getDifficulty());

        List<ExerciseItemResponse> copiedItems = original.getItems().stream()
                .map(item -> {
                    if (item instanceof VideoExerciseResponse videoItem) {
                        VideoExerciseResponse videoCopy = new VideoExerciseResponse();
                        videoCopy.setId(videoItem.getId());
                        videoCopy.setVideoKey(videoItem.getVideoKey());
                        videoCopy.setSrtKey(videoItem.getSrtKey());
                        return videoCopy;
                    }
                    if (item instanceof RetellingExerciseResponse retellingItem) {
                        // 直接返回原始对象，因为它是不可变的(只有String)
                        // 或者创建一个新的拷贝
                        return new RetellingExerciseResponse(retellingItem.getId(), retellingItem.getContent());
                    }
                    return null;
                })
                .collect(Collectors.toList());

        copy.setItems(copiedItems);
        return copy;
    }
}