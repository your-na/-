package com.study.board.service;

import com.study.board.entity.User;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // BCrypt 인코더 초기화
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
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user); // 사용자 정보 저장
    }

    public boolean validateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 입력된 비밀번호와 저장된 비밀번호 비교
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            return false;
        }
    }
}