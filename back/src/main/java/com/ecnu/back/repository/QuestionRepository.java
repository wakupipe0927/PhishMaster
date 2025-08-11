package com.ecnu.back.repository;

import com.ecnu.back.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 继承 JpaRepository 后可自动拥有 CRUD 功能
}

