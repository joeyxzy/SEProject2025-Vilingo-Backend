package com.Vilingo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class YoudaoApiSignUtil {

    /**
     * 生成有道API签名
     * @param appKey 应用ID
     * @param appSecret 应用密钥
     * @param query 要翻译的单词
     * @param salt 随机数 (UUID)
     * @param curtime 当前时间戳 (秒)
     * @return SHA256签名字符串
     */
    public static String
    generateSign(String appKey, String appSecret, String query, String salt, String curtime) {
        String input = truncate(query);
        String strToSign = appKey + input + salt + curtime + appSecret;
        return getSha256(strToSign);
    }

    /**
     * 对输入字符串进行SHA-256哈希
     */
    private static String getSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * 根据有道API文档规则对查询字符串进行截取
     */
    private static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        if (len <= 20) {
            return q;
        }
        return q.substring(0, 10) + len + q.substring(len - 10, len);
    }
}