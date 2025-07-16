package com.example.springbootjpaexample.board.service;

import com.example.springbootjpaexample.board.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByPostId(Long postId);

    Long addComment(CommentDTO dto);

    void deleteComment(Long id);
}
