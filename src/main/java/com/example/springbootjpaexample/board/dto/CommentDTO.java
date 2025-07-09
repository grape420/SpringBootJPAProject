package com.example.springbootjpaexample.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private Long postId;
}
