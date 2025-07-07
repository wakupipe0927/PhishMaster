package com.example.demo0706.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo0706.dto.LoginDTO;
import com.example.demo0706.dto.RegisterDTO;
import com.example.demo0706.entity.User;
import com.example.demo0706.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    // 用户注册
    public Map<String, Object> register(RegisterDTO registerDTO) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查用户名是否已存在
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }
        
        // 检查学号是否已存在
        if (userRepository.existsByStudentId(registerDTO.getStudentId())) {
            result.put("success", false);
            result.put("message", "学号已被注册");
            return result;
        }
        
        // 创建新用户
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setStudentId(registerDTO.getStudentId());
        
        // 保存用户
        User savedUser = userRepository.save(user);
        
        result.put("success", true);
        result.put("message", "注册成功");
        result.put("userId", savedUser.getId());
        
        return result;
    }
    
    // 用户登录
    public Map<String, Object> login(LoginDTO loginDTO) {
        Map<String, Object> result = new HashMap<>();
        
        // 根据用户名查找用户
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        
        if (user == null) {
            result.put("success", false);
            result.put("message", "用户名或学号错误");
            return result;
        }
        
        // 验证学号
        if (!user.getStudentId().equals(loginDTO.getStudentId())) {
            result.put("success", false);
            result.put("message", "用户名或学号错误");
            return result;
        }
        
        result.put("success", true);
        result.put("message", "登录成功");
        result.put("userId", user.getId());
        result.put("username", user.getUsername());
        result.put("studentId", user.getStudentId());
        
        return result;
    }
}
