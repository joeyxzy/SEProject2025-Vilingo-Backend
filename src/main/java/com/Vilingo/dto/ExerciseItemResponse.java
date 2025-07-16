package com.Vilingo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

// 下面这两个 Jackson 注解是实现多态序列化的关键
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,   // 使用名字作为类型标识符
        include = JsonTypeInfo.As.PROPERTY, // 将类型标识符作为一个属性
        property = "type"             // 这个属性的名字叫 "type"
)
@JsonSubTypes({
        // 在这里注册所有可能的子类
        @JsonSubTypes.Type(value = VideoExerciseResponse.class, name = "video")
        // 如果未来有复述练习，可以加在这里：
        // @JsonSubTypes.Type(value = RetellingExerciseResponse.class, name = "retelling")
})
@Data
public abstract class ExerciseItemResponse {
    private int id; // 每个练习项的唯一ID
    //private String type; // 类型，例如 "video"
}