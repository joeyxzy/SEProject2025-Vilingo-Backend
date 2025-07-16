package com.Vilingo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TranslationResponse(
        // @JsonProperty 用于将 Java 的驼峰命名 (camelCase) 映射到 JSON 的下划线命名 (snake_case)
        @JsonProperty("simple_trans")
        String simpleTrans,

        @JsonProperty("more_trans")
        String moreTrans
) {}