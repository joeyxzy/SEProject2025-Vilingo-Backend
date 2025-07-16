package com.Vilingo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Lombok: 自动生成 getter, setter, toString, equals, hashCode
@NoArgsConstructor // Lombok: 自动生成无参构造函数
@AllArgsConstructor // Lombok: 自动生成全参构造函数
public class LessonInfoResponse {
    private int id;
    private String description;
    private List<Integer> exercises;
}