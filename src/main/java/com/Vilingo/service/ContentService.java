package com.Vilingo.service; // 请替换为你的包名

import com.Vilingo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final StaticContentService staticContentService;
    private final OssService ossService;

    // getSections 方法保持不变
    public List<SectionInfoResponse> getSections(String lang) {
        // 直接从静态数据仓库获取预先定义好的课程结构
        if(lang.equals("en"))
            return staticContentService.getEnFullContent();
        else
            return null;
    }

    /**
     * 新逻辑：根据ID获取单个练习的详细信息，
     * 并将所有关联的练习项（Video 和 Retelling）一次性返回。
     */
    public ExerciseInfoResponse getExercise(Integer id) {
        // 1. 从静态数据仓库获取原始练习数据 (items 列表包含两种类型)
        ExerciseInfoResponse originalExercise = staticContentService.findExerciseById(id);

        if (originalExercise == null) {
            return null; // ID 不存在，返回 null
        }

        // 2. 创建一个深拷贝，以避免修改静态缓存
        ExerciseInfoResponse exerciseToReturn = deepCopy(originalExercise);

        // 3. 遍历所有练习项，为其中的 Video 项生成 URL
        exerciseToReturn.getItems().forEach(item -> {
            if (item instanceof VideoExerciseResponse videoItem) {
                String videoUrl = ossService.generatePresignedUrl(videoItem.getVideoKey());
                String srtUrl = ossService.generatePresignedUrl(videoItem.getSrtKey());
                videoItem.setVideo(videoUrl);
                videoItem.setSrt(srtUrl);
            }
            // Retelling 项不需要特殊处理，直接返回即可
        });

        // 4. 返回包含了所有练习项的完整 Exercise DTO
        return exerciseToReturn;
    }

    // deepCopy 方法现在是正确的，因为它已经支持处理两种类型的 item
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
                        return new RetellingExerciseResponse(retellingItem.getId(), retellingItem.getContent());
                    }
                    return null;
                })
                .collect(Collectors.toList());

        copy.setItems(copiedItems);
        return copy;
    }
}