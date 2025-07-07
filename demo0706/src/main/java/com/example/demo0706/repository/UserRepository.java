package com.example.demo0706.repository;

import java.util.Optional;


import com.example.demo0706.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    // 根据用户名查找用户
    Optional<User> findByUsername(String username);
    
    // 根据学号查找用户
    Optional<User> findByStudentId(String studentId);
    
    // 检查用户名是否存在
    boolean existsByUsername(String username);
    
    // 检查学号是否存在
    boolean existsByStudentId(String studentId);
}
