package com.example.demo0706.dto;

public class RegisterDTO {
    private String username;
    private String studentId;
    
    // 构造方法
    public RegisterDTO() {}
    
    public RegisterDTO(String username, String studentId) {
        this.username = username;
        this.studentId = studentId;
    }
    
    // getter 和 setter
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
