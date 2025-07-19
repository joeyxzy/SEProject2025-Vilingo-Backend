package com.Vilingo.controller;

import com.Vilingo.dto.RecommendVideoFetchResponse;
import com.Vilingo.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    /**
     * 处理 GET /recommend/get 请求
     * @param before 分页游标，指向上次请求的最后一条记录的ID (可选)
     * @return 推荐视频列表的响应
     */
    @GetMapping("/get")
    public ResponseEntity<RecommendVideoFetchResponse> getRecommendedVideos(
            @RequestParam(required = false) Integer before) {

        // 调用服务层获取处理好的数据
        RecommendVideoFetchResponse response = recommendService.getRecommendations(before);

        // 将结果包装在 200 OK 响应中返回
        return ResponseEntity.ok(response);
    }
}