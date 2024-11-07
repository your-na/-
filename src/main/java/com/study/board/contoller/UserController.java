package com.study.board.contoller;


import com.study.board.entity.User;
import com.study.board.repository.UserRepository;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll(); // 모든 사용자 정보 반환
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username).orElse(null); // 특정 사용자 반환
    }


    // 회원가입 페이지를 보여줍니다.
    @GetMapping("/join")
    public String joinForm() {
        return "join"; // join.html을 반환합니다.
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(User user, Model model) {
        try {
            userService.saveUser(user); // 사용자 저장 시도
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/api/user/login"; // 회원가입 후 보드 리스트 페이지로 리다이렉트
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage()); // 에러 메시지 추가
            return "/api/user/join"; // 에러 발생 시 join.html로 다시 리턴
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/api/user/login"; // login.html 파일을 반환
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.validateUser(username, password)) {
            // 세션에 사용자 정보를 저장하는 로직 추가 가능
            return "redirect:/board/list"; // 로그인 성공 시 홈 페이지로 리다이렉트
        } else {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀립니다.");
            return "/api/user/login"; // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }

}
