package com.ecnu.back.controller;

import com.ecnu.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin// 允许跨域请求
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    // 用户注册接口
    @GetMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String username) {

        int result = userService.register(username, email, password);
        
        if (result == 1) {
            return "ok";
        } else if (result == 0) {
            return "注册失败, 用户已存在";
        } else {
            return "注册失败, 未知错误";
        }
    }
    
    // 用户登录接口
    @GetMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        int result = userService.login(email,  password);
        
        if (result == 2) {
            return "ok";
        } else {
            return "登陆失败";
        }
    }
    
    // 测试接口
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("认证服务正常运行！");
    }
}
