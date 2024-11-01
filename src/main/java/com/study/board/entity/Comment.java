package com.study.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    Integer id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;
    @ManyToOne
    private Board board;
}
