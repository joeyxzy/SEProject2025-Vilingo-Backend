package com.Vilingo.controller;

import com.Vilingo.service.OssService;
import com.Vilingo.util.FileMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/video")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 根据文件ID获取OSS临时访问链接
     * 示例请求：GET /api/oss/file?id=1
     */
    @GetMapping("/subtitle")
    public Map<String, Object> getSignedFileUrl(@RequestParam Integer id) {
        Map<String, Object> response = new HashMap<>();

        // 1. 参数校验
        if (id == null) {
            response.put("error", "缺少必要参数: id");
            response.put("code", "MISSING_PARAMETER");
            return response;
        }

        // 2. 查找映射文件名
        String fileName = FileMapping.getFileNameById(id.toString());
        if (fileName == null) {
            response.put("error", "找不到与 id = " + id + " 对应的文件");
            response.put("code", "FILE_NOT_FOUND");
            return response;
        }


        // 4. 调用服务生成 URL
        String url = ossService.generatePresignedUrl(fileName);

        // 5. 返回成功结果
        response.put("url", url);
        return response;
    }
}