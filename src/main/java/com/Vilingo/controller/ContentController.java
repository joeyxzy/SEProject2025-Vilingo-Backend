package com.Vilingo.controller;

import com.Vilingo.dto.ExerciseInfoResponse;
import com.Vilingo.dto.SectionInfoResponse;
import com.Vilingo.dto.SectionListResponse;
import com.Vilingo.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 负责处理所有与课程内容相关的API请求。
 * URL路径以 /content 开头。
 */
@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    /**
     * 处理 GET /content/sections 请求。
     * 获取给定语言的所有章节列表。
     *
     * @param lang 指定的语言参数 (e.g., "en", "zh")
     * @return 一个包含所有章节信息的JSON响应
     */
    @GetMapping("/sections")
    public ResponseEntity<SectionListResponse> getSections(@RequestParam String lang) {
        // 1. 调用服务获取数据，返回值可能是 List，也可能是 null
        List<SectionInfoResponse> sectionsData = contentService.getSections(lang);

        // 2. 检查返回值
        if (sectionsData == null) {
            // 如果是 null，说明没找到，返回 404
            return ResponseEntity.notFound().build();
        } else {
            // 如果不是 null，说明找到了数据
            // 将数据包装成最终响应对象
            SectionListResponse response = new SectionListResponse(sectionsData);
            // 返回 200 OK 和响应体
            return ResponseEntity.ok(response);
        }
    }

    /**
     * 处理 GET /content/exercise 请求。
     * 根据ID获取单个练习的详细信息。
     *
     * @param id 练习的唯一ID
     * @return 包含练习详情的JSON响应，或在找不到时返回404
     */
    @GetMapping("/exercise")
    public ResponseEntity<ExerciseInfoResponse> getExercise(@RequestParam Integer id) {
        // 1. 调用服务获取练习数据
        ExerciseInfoResponse exercise = contentService.getExercise(id);

        // 2. 检查服务层是否找到了对应的练习
        if (exercise == null) {
            // 如果返回null，说明ID无效，向客户端返回HTTP 404 Not Found状态码
            return ResponseEntity.notFound().build();
        }

        // 3. 如果找到了练习，返回HTTP 200 OK响应，主体为JSON化的练习对象
        return ResponseEntity.ok(exercise);
    }
}