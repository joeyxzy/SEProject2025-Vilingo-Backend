package com.Vilingo.service;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import lombok.RequiredArgsConstructor; // 推荐使用 Lombok 来简化构造函数
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;

@Service
// @RequiredArgsConstructor 会为所有 final 字段自动生成一个构造函数。
// 这是 Spring 推荐的依赖注入方式。
@RequiredArgsConstructor
public class OssService {

    // 1. 声明为 final，并通过构造函数注入
    private final OSS ossClient;

    // 2. 从配置文件中注入 bucketName
    @Value("${aliyun.oss.bucket-name}")

    private String bucketName;

    // （可选但推荐）从配置文件注入默认过期时间
    @Value("${aliyun.oss.url-expire-minutes}")
    private long defaultExpireMinutes;


    /**
     * 为OSS文件生成一个预签名的访问URL。
     *
     * @param objectKey     文件在OSS中的完整路径 (例如: "videos/intro.mp4")
     * @return              生成的带有签名的URL字符串
     */
    public String generatePresignedUrl(String objectKey) {
        // 使用默认的过期时间
        long timeoutMillis = defaultExpireMinutes * 60 * 1000;
        return generatePresignedUrl(objectKey, timeoutMillis);
    }


    /**
     * 为OSS文件生成一个预签名的访问URL，并指定过期时间。
     * 这是你原始方法 `getFileUrl` 的改进版本。
     *
     * @param objectKey     文件在OSS中的完整路径 (例如: "videos/intro.mp4")
     * @param timeoutMillis URL的有效时间（毫秒）
     * @return              生成的带有签名的URL字符串
     */
    public String generatePresignedUrl(String objectKey, long timeoutMillis) {
        if (objectKey == null || objectKey.isBlank()) {
            return null;
        }

        // 3. 直接使用注入的 ossClient，不再创建和关闭它
        Date expiration = new Date(System.currentTimeMillis() + timeoutMillis);

        // 使用注入的 ossClient 生成 URL。
        // 不再需要 try-finally 和 shutdown()
        URL url = ossClient.generatePresignedUrl(bucketName, objectKey, expiration, HttpMethod.GET);

        return url.toString();
    }
}