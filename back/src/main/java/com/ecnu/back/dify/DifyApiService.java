
package com.ecnu.back.dify;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
public class DifyApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    // 替换为你的 API Key 和 URL
    private static final String API_URL = "http://localhost/v1/chat-messages"; // 去掉 POST
    private static final String API_KEY = "app-xKiEZC47x7K31wCDlKQYjSyk";

    public String getResponse(String userMessage) {
        // 构造 inputs（可为空对象）
        Map<String, Object> inputs = new HashMap<>();

        // 构造 file 对象（可选）
        Map<String, Object> file = new HashMap<>();
        file.put("type", "image");
        file.put("transfer_method", "remote_url");
        file.put("url", "https://cloud.dify.ai/logo/logo-site.png");

        // 构造请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("inputs", inputs);
        requestBody.put("query", userMessage);
        requestBody.put("response_mode", "blocking"); // 或 blocking
        requestBody.put("conversation_id", "a721ad43-d040-4350-a670-f48d4ac8e670");
        requestBody.put("user", "abc");
        System.out.println("Request Body: " + requestBody);

        requestBody.put("files", Collections.singletonList(file));

        // 构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 发起请求
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

        // 返回结果
        return response.getBody();
    }
}
