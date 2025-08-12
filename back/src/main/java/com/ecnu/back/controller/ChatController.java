package com.ecnu.back.controller;

import com.ecnu.back.dify.DifyApiService;
import com.ecnu.back.model.ChatRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment; // 正确导入 Spring 的 Environment


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin // 允许跨域访问
public class ChatController {

    private final String MAXKB_BASE_URL = "http://10005480di8ni.vicp.fun/chat/api";
    private final String APP_ID = "01983f1d-b566-7783-b10b-1cb9fd8c3b68";
    private final String API_TOKEN = "application-6c5656e8e4581f898e68ec8b41d3d606";

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @GetMapping("/session/open")
    public ResponseEntity<?> openSession(){

        try{
            //构造请求的URL
            String url = MAXKB_BASE_URL + "/open?app_id=" + APP_ID;
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", "Bearer " + API_TOKEN);

            try {
                //执行HTTP请求
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    //获取响应体
                    String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                    return ResponseEntity.ok(result);//返回响应内容
                }catch (ParseException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("解析响应错误");
                }

            }catch (IOException e)
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请求处理错误");
            }

        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取会话失败" + e.getMessage());
        }
    }

    @PostMapping("/chat")
    public ResponseEntity<?> sendMessage(@RequestBody ChatRequest chatRequest){
        try
        {
            String url = MAXKB_BASE_URL + "/chat_message/" + chatRequest.getChatID();
            HttpPost post = new HttpPost(url);
            post.setHeader("Authorization", "Bearer " + API_TOKEN);
            post.setHeader("Content-Type", "application/json");

            //构建请求体
            ObjectMapper mapper = new ObjectMapper();
            Map<String , Object> body = new HashMap<>();
            body.put("message", chatRequest.getMessage());
            body.put ("chat_record_id", chatRequest.getChatID());
            body.put("stream", false);
            body.put("re_chat", chatRequest.getRe_chat());

            String jsonBody = mapper.writeValueAsString(body);
            post.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("发送失败: " + e.getMessage());
        }
    }
}








