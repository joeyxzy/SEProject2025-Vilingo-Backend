package com.Vilingo.dto; // 请确保包名是你自己的

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

// 忽略未知的JSON字段，保持健壮性
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YoudaoTranslationResult {

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("query")
    private String query;

    @JsonProperty("translation")
    private List<String> translation;

    @JsonProperty("l")
    private String languagePair;

    // 关键修改：将 dict 和 webdict 定义为嵌套的静态类
    @JsonProperty("dict")
    private DeepLink dict;

    @JsonProperty("webdict")
    private DeepLink webdict;

    /**
     *  用于映射 "dict" 和 "webdict" 这种 { "url": "..." } 结构的内部静态类
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class DeepLink {
        @JsonProperty("url")
        private String url;
    }
}