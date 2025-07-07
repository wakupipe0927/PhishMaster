package com.example.demo0706.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo0706.dto.LoginDTO;
import com.example.demo0706.dto.RegisterDTO;
import com.example.demo0706.service.UserService;

@RestController
@CrossOrigin// 允许跨域请求
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    // 用户注册接口
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterDTO registerDTO) {
        Map<String, Object> result = userService.register(registerDTO);
        
        if ((Boolean) result.get("success")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    // 用户登录接口
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> result = userService.login(loginDTO);
        
        if ((Boolean) result.get("success")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
    
    // 测试接口
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("认证服务正常运行！");
    }
}
