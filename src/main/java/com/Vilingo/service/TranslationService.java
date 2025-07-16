package com.Vilingo.service; // 请确保包名是你自己的

import com.Vilingo.dto.TranslationResponse;
import com.Vilingo.dto.YoudaoTranslationResult;
import com.Vilingo.util.YoudaoApiSignUtil;
import lombok.RequiredArgsConstructor;
// 1. 引入日志相关的类
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TranslationService {

    // 2. 创建一个静态的、final的日志记录器实例
    private static final Logger log = LoggerFactory.getLogger(TranslationService.class);

    private final WebClient.Builder webClientBuilder;

    @Value("${translation.youdao.api.url}")
    private String apiUrl;
    @Value("${translation.youdao.api.app-key}")
    private String appKey;
    @Value("${translation.youdao.api.app-secret}")
    private String appSecret;

    public Optional<TranslationResponse> getTranslation(String word) {
        WebClient webClient = webClientBuilder.build();
        String salt = UUID.randomUUID().toString();
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        String sign = YoudaoApiSignUtil.generateSign(appKey, appSecret, word, salt, curtime);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("q", word);
        params.add("from", "en");
        params.add("to", "zh-CHS");
        params.add("appKey", appKey);
        params.add("salt", salt);
        params.add("sign", sign);
        params.add("signType", "v3");
        params.add("curtime", curtime);

        // 3. 在发起请求前，打印出我们要发送的参数
        log.info("--- [有道API] 正在发送请求，参数: {}", params);

        return webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(params))
                .retrieve()
                .bodyToMono(YoudaoTranslationResult.class)
                // 4. 使用 doOnNext 操作符，在不中断流的情况下，打印出成功解析后的对象
                .doOnNext(youdaoResult -> {
                    log.info("--- [有道API] 收到并成功解析响应: {}", youdaoResult);
                })
                .flatMap(this::convertToInternalResponse)
                // 5. 使用 onErrorResume 捕获任何错误，并打印日志
                .onErrorResume(e -> {
                    log.error("--- [有道API] 调用失败，发生异常!", e);
                    // 返回一个空的 Mono，这样程序不会崩溃，而是会走向 404 的逻辑
                    return Mono.empty();
                })
                .block();
    }

    private Mono<Optional<TranslationResponse>> convertToInternalResponse(YoudaoTranslationResult result) {
        // 6. 在转换逻辑里，对失败情况也加入日志
        if (!"0".equals(result.getErrorCode()) || result.getTranslation() == null || result.getTranslation().isEmpty()) {
            log.warn("--- [业务逻辑] 有道返回错误码或无翻译结果，errorCode: {}", result.getErrorCode());
            return Mono.just(Optional.empty());
        }

        List<String> allTranslations = result.getTranslation();
        String simpleTrans = allTranslations.get(0);
        String moreTrans = String.join("；", allTranslations);

        TranslationResponse response = new TranslationResponse(simpleTrans, moreTrans);
        return Mono.just(Optional.of(response));
    }
}