package com.Vilingo.dto;

import java.util.List;

// 使用 Java 16+ 的 Record，非常适合创建不可变的 DTO
public record WordlistFetchInfoResponse(
        int count,
        List<String> list,
        boolean hasNextPage,
        String nextWord
) {}