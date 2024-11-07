package com.study.board.contoller;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    //@Autowired
    //private BoardRepository boardRepository;

    @GetMapping("/main")
    public String mainPage(Model model) {
        // 게시글 목록을 DB에서 가져옴
        //List<Board> hotPosts = boardRepository.findAll(); // DB에서 게시글 목록을 가져오기

        // 모델에 게시글 목록 추가
        //model.addAttribute("hotPosts", hotPosts);

        return "main"; // main 페이지로 이동
    }
}
