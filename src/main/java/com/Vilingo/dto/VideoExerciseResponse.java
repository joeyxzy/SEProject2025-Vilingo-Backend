package com.Vilingo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) // 确保 equals 和 hashCode 包含父类的字段
public class VideoExerciseResponse extends ExerciseItemResponse {

    // 这两个字段将包含最终从OSS生成的URL，返回给前端
    private String video;
    private String srt;

    // *** 这是一个重要的设计技巧 ***
    // 这两个字段用来在静态数据中存储OSS的Key（文件名）
    // @JsonIgnore 确保它们不会被序列化到最终的JSON响应中
    @JsonIgnore
    private String videoKey;
    @JsonIgnore
    private String srtKey;
}