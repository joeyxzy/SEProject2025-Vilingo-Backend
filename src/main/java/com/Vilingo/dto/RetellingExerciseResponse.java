package com.Vilingo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) // 确保包含父类的字段
public class RetellingExerciseResponse extends ExerciseItemResponse {

    private String content; // 复述练习的内容

    // 为了在 StaticContentService 中方便地创建对象，可以加一个构造函数
    public RetellingExerciseResponse(int id, String content) {
        this.setId(id); // 设置从父类继承的id
        this.content = content;
    }
}