package com.Vilingo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionInfoResponse {
    private int id;
    private String title;
    private List<ChapterInfoResponse> chapters;
}