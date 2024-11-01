package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.Comment;
import com.study.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public void create(Board board, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setBoard(board);
        this.commentRepository.save(comment);

    }
}
