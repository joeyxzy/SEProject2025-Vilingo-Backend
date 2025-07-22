package com.Vilingo.service; // 请替换为您的包名

import com.Vilingo.dto.RecommendVideoFetchResponse;
import com.Vilingo.dto.RecommendVideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final StaticContentService staticContentService;
    private final OssService ossService;

    private static final int PAGE_SIZE = 8;

    public RecommendVideoFetchResponse getRecommendations(Integer before, String status) {
        // 1. 获取所有视频的原始列表
        List<RecommendVideoResponse> allVideos = staticContentService.getRecommendedVideos();

        // 2. 核心：调用新的、基于章节索引的排序方法
        List<RecommendVideoResponse> sortedVideos = sortVideosByChapterIndex(allVideos, status);

        // 3. 后续的分页逻辑完全建立在“排序后”的列表之上，这部分无需修改
        int startIndex = 0;
        if (before != null) {
            for (int i = 0; i < sortedVideos.size(); i++) {
                if (sortedVideos.get(i).getId() == before) {
                    startIndex = i + 1;
                    break;
                }
            }
        }

        List<RecommendVideoResponse> pageVideos = new ArrayList<>();
        int endIndex = Math.min(startIndex + PAGE_SIZE, sortedVideos.size());
        for (int i = startIndex; i < endIndex; i++) {
            pageVideos.add(sortedVideos.get(i));
        }

        boolean hasNextPage = endIndex < sortedVideos.size();
        String nextCursor = null;
        if (!pageVideos.isEmpty() && hasNextPage) {
            nextCursor = String.valueOf(pageVideos.get(pageVideos.size() - 1).getId());
        }

        List<RecommendVideoResponse> processedData = pageVideos.stream()
                .map(this::createDeepCopyAndGenerateUrl)
                .toList();

        return new RecommendVideoFetchResponse(
                processedData,
                hasNextPage,
                processedData.size(),
                nextCursor
        );
    }

    /**
     * 新的核心排序逻辑：根据前端传递的 1-8 的章节索引，对视频进行排序。
     */
    private List<RecommendVideoResponse> sortVideosByChapterIndex(List<RecommendVideoResponse> videos, String status) {
        // 步骤 A: 首先，按难度将所有视频分组
        Map<String, List<RecommendVideoResponse>> videosByDifficulty = videos.stream()
                .collect(Collectors.groupingBy(RecommendVideoResponse::getDifficulty));

        List<RecommendVideoResponse> easyVideos = videosByDifficulty.getOrDefault("easy", new ArrayList<>());
        List<RecommendVideoResponse> mediumVideos = videosByDifficulty.getOrDefault("medium", new ArrayList<>());
        List<RecommendVideoResponse> hardVideos = videosByDifficulty.getOrDefault("hard", new ArrayList<>());

        // 步骤 B: 从 status 字符串中解析出 chapter 索引 (1-8)
        int chapterIndex = parseChapterIndexFromStatus(status);

        // 步骤 C: 根据 chapter 索引决定最终的拼接顺序
        List<RecommendVideoResponse> sortedList = new ArrayList<>();

        // 规则 1: 进度在第 1-2 章 -> 优先推荐 easy
        if (chapterIndex <= 2) {
            sortedList.addAll(easyVideos);
            sortedList.addAll(mediumVideos);
            sortedList.addAll(hardVideos);
        }
        // 规则 2: 进度在第 3-4 章 -> 优先推荐 medium
        else if (chapterIndex <= 4) {
            sortedList.addAll(mediumVideos);
            sortedList.addAll(hardVideos);
            sortedList.addAll(easyVideos);
        }
        // 规则 3: 进度在第 5-8 章 -> 优先推荐 hard
        else {
            sortedList.addAll(hardVideos);
            sortedList.addAll(easyVideos);
            sortedList.addAll(mediumVideos);
        }

        return sortedList;
    }

    /**
     * 辅助方法：从 status 字符串中安全地解析出 chapter 索引 (1-8)。
     */
    private int parseChapterIndexFromStatus(String status) {
        // 默认返回 1，代表第一章，会触发 easy 优先逻辑
        final int DEFAULT_CHAPTER_INDEX = 1;

        if (status == null || status.isBlank()) {
            return DEFAULT_CHAPTER_INDEX;
        }
        try {
            String[] parts = status.split("-");
            if (parts.length >= 2) {
                return Integer.parseInt(parts[1]); // 返回第二个位置的值，即 chapter 索引
            }
        } catch (Exception e) {
            // 解析失败，同样返回默认值
            return DEFAULT_CHAPTER_INDEX;
        }
        return DEFAULT_CHAPTER_INDEX;
    }

    /**
     * 辅助方法：深拷贝并生成URL (保持不变)
     */
    private RecommendVideoResponse createDeepCopyAndGenerateUrl(RecommendVideoResponse original) {
        RecommendVideoResponse copy = new RecommendVideoResponse();
        copy.setId(original.getId());
        copy.setTitle(original.getTitle());
        copy.setHeight(original.getHeight());
        copy.setEid(original.getEid());
        // 为封面图片生成带签名的URL
        copy.setCover(ossService.generatePresignedUrl(original.getCoverKey()));
        return copy;
    }
}