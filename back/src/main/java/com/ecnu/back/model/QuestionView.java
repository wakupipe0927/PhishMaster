package com.ecnu.back.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionView {
    private Long id;
    private String title;
    private List<String> options;

    public QuestionView(Long id, String title, List<String> options) {
        this.id = id;
        this.title = title;
        this.options = options;
    }

    // 也可以加一个无参构造函数（若未加 Lombok 注解的话）
    public QuestionView() {}
}
