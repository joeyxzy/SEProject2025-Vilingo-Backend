package com.Vilingo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterInfoResponse {
    private int id;
    private String title;
    private String description;
    private List<LessonInfoResponse> lessons;
}