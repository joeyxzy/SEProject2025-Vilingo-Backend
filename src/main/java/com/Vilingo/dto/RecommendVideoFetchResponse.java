package com.Vilingo.dto;

import java.util.List;

public record RecommendVideoFetchResponse(
        List<RecommendVideoResponse> data,
        boolean hasNextPage,
        int count,
        // API 文档指定游标是字符串类型
        String nextCursor
) {}