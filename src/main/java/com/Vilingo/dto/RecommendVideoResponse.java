package com.Vilingo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RecommendVideoResponse {

    private int id;
    private String title;
    // 这个字段将包含最终生成的、可公开访问的封面图片URL
    private String cover;
    private int height;
    private int eid; // 关联的练习ID

    // 我们使用和之前一样的模式：在内部存储OSS的Key
    // 并用 @JsonIgnore 确保它不会出现在最终的JSON响应中。
    @JsonIgnore
    private String coverKey;
}