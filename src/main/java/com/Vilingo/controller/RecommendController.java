package com.Vilingo.controller; // 请替换为你的包名

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
     * @param before 分页游标 (可选)
     * @param status 用户学习进度 (可选)
     * @return 推荐视频列表的响应，每页固定8条
     */
    @GetMapping("/get")
    public ResponseEntity<RecommendVideoFetchResponse> getRecommendedVideos(
            @RequestParam(required = false) Integer before,
            @RequestParam(required = false) String status) {
        // 核心修改：移除了 @RequestParam int limit 参数

        // 调用更新后的 service 方法，它不再需要 limit 参数
        RecommendVideoFetchResponse response = recommendService.getRecommendations(before, status);

        // 将结果包装在 200 OK 响应中返回
        return ResponseEntity.ok(response);
    }
}