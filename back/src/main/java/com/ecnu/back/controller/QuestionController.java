package com.ecnu.back.controller;

import com.ecnu.back.model.Question;
import com.ecnu.back.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // 获取一题随机题目（不包含答案和解析）
    @GetMapping("/getQuestion")
    public Map<String, Object> getRandomQuestion() {
        List<Question> allQuestions = questionRepository.findAll();
        Question q = allQuestions.get(new Random().nextInt(allQuestions.size()));

        Map<String, Object> questionData = new HashMap<>();
        questionData.put("questionId", q.getId());
        questionData.put("title", q.getTitle());
        questionData.put("options", q.getOptions());
        questionData.put("type", q.getQuestionType());

        Map<String, Object> res = new HashMap<>();
        //res.put("status", "success");
        res.put("question", questionData);

        return res;
    }

    @PostMapping("/submitAnswer")
    public Map<String, Object> submitAnswer(@RequestBody Map<String, String> payload) {
        Long questionId = Long.parseLong(payload.get("questionId"));
        String userAnswer = payload.get("userAnswer");

        Optional<Question> optional = questionRepository.findById(questionId);
        Map<String, Object> res = new HashMap<>();

        if (optional.isEmpty()) {
            res.put("message", "题目不存在");
            return res;
        }

        Question q = optional.get();

        // 统一转为大写，分割为Set
        Set<String> correctSet = Arrays.stream(q.getCorrectAnswer().toUpperCase().split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
        Set<String> userSet = Arrays.stream(userAnswer.toUpperCase().split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

        boolean isCorrect = correctSet.equals(userSet);

        res.put("isCorrect", isCorrect);
        res.put("correctAnswer", q.getCorrectAnswer());
        res.put("explanation", q.getExplanation());
        return res;
    }


}
