package com.study.board.contoller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import com.study.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/comment/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Board board = this.boardService.getBoard(id);
        this.commentService.create(board, content);
        return String.format("redirect:/board/view?id=%s", id);
    }
}
