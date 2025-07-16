package com.Vilingo.dto;

import lombok.Data;
import java.util.List;

@Data
public class ExerciseInfoResponse {
    private int xp;
    private String difficulty;
    private List<ExerciseItemResponse> items;
}