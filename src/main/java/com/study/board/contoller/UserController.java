package com.study.board.contoller;


import com.study.board.entity.User;
import com.study.board.repository.UserRepository;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.study.board.dto.UserDTO;


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
            return "join"; // 에러 발생 시 join.html로 다시 리턴
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "/login"; // login.html 파일을 반환
    }
    // 로그인 처리 (POST 요청)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO loginRequest) {
        try {
            boolean isValidUser = userService.validateUser(loginRequest.getUsername(), loginRequest.getPassword());

            if (isValidUser) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }



    @GetMapping("/main")
    public String mainPage(Model model) {
        // 게시글 목록을 DB에서 가져옴
        //List<Board> hotPosts = boardRepository.findAll(); // DB에서 게시글 목록을 가져오기

        // 모델에 게시글 목록 추가
        //model.addAttribute("hotPosts", hotPosts);

        return "/main"; // main 페이지로 이동
    }


}
