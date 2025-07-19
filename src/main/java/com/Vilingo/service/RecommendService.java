package com.Vilingo.service;

import com.Vilingo.dto.RecommendVideoFetchResponse;
import com.Vilingo.dto.RecommendVideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final StaticContentService staticContentService;
    private final OssService ossService;

    // 将每页数量定义为常量，方便未来修改
    private static final int PAGE_SIZE = 6;

    /**
     * 获取推荐视频信息流，并处理分页逻辑。
     * @param before 上一页最后一条记录的ID，作为分页游标。如果为null，则从头开始。
     * @return 组装好的、包含分页信息和URL的响应对象。
     */
    public RecommendVideoFetchResponse getRecommendations(Integer before) {
        // 1. 从静态数据源获取所有“被推荐”视频的完整列表
        List<RecommendVideoResponse> allVideos = staticContentService.getRecommendedVideos();

        // 2. 根据游标(before)找到分页的起始索引
        int startIndex = 0;
        if (before != null) {
            // 遍历整个列表，找到游标所在的位置
            for (int i = 0; i < allVideos.size(); i++) {
                if (allVideos.get(i).getId() == before) {
                    startIndex = i + 1; // 分页从游标的下一个元素开始
                    break;
                }
            }
        }

        // 3. 根据起始索引和固定的页面大小，获取当前页的子列表
        List<RecommendVideoResponse> pageVideos = new ArrayList<>();
        // 计算结束索引，并用 Math.min 防止数组越界
        int endIndex = Math.min(startIndex + PAGE_SIZE, allVideos.size());
        for (int i = startIndex; i < endIndex; i++) {
            pageVideos.add(allVideos.get(i));
        }

        // 4. 判断是否存在下一页
        boolean hasNextPage = endIndex < allVideos.size();

        // 5. 生成用于下一页的游标 (nextCursor)
        String nextCursor = null;
        // 只有在当前页不为空且存在下一页时，才生成游标
        if (!pageVideos.isEmpty() && hasNextPage) {
            // 下一页的游标是当前页最后一个元素的 ID
            nextCursor = String.valueOf(pageVideos.get(pageVideos.size() - 1).getId());
        }

        // 6. 处理当前页的数据：为每个视频的封面生成URL，并进行深拷贝以防修改缓存
        List<RecommendVideoResponse> processedData = pageVideos.stream()
                .map(this::createDeepCopyAndGenerateUrl)
                .toList();

        // 7. 组装并返回最终的响应对象
        return new RecommendVideoFetchResponse(
                processedData,
                hasNextPage,
                processedData.size(),
                nextCursor
        );
    }

    /**
     * 对单个推荐视频对象进行深拷贝，并为其封面生成URL。
     * @param original 包含OSS Key的原始对象
     * @return 包含完整URL的全新对象
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