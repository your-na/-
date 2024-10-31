package com.study.board.contoller;


import com.study.board.entity.User;
import com.study.board.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 페이지를 보여줍니다.
    @GetMapping("/user/join")
    public String joinForm() {
        return "join"; // join.html을 반환합니다.
    }

    // 회원가입 처리
    @PostMapping("/user/join")
    public String join(User user, Model model) {
        try {
            userService.saveUser(user); // 사용자 저장 시도
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            return "redirect:/board/list"; // 회원가입 후 보드 리스트 페이지로 리다이렉트
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage()); // 에러 메시지 추가
            return "/board/write"; // 에러 발생 시 join.html로 다시 리턴
        }
    }
}
