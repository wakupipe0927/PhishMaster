package com.ecnu.back.model;

import jakarta.persistence.*;
import java.util.List;
import com.ecnu.back.util.StringListJsonConverter;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "question_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "json")
    @Convert(converter = StringListJsonConverter.class)
    private List<String> options;

    @Column(name = "correct_answer")
    private String correctAnswer;

    private String explanation;

    @Column(name = "question_type")
    private String questionType; // 值为 "single" 或 "multiple"


    // ====== 手写 getter 方法 ↓↓↓ ======

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getQuestionType() {
        return questionType;
    }

    // 可选：也可以加构造函数（如果你用了 new Question(...））

    public Question() {}


    public Question(Long id, String title, List<String> options, String correctAnswer, String explanation, String questionType) {
        this.id = id;
        this.title = title;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.questionType = questionType;
    }

}
