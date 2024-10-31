package com.study.board.service;

import com.study.board.entity.User;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    // 사용자 저장 메서드
    public User saveUser(User user) {
        // 아이디 중복 체크
        if (isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다."); // 예외 처리
        }
        return userRepository.save(user); // 사용자 정보 저장
    }
}
