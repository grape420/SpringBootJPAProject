package com.example.springbootjpaexample.board.service.impl;

import com.example.springbootjpaexample.board.dto.CommentDTO;
import com.example.springbootjpaexample.board.repository.CommentRepository;
import com.example.springbootjpaexample.board.repository.PostRepository;
import com.example.springbootjpaexample.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        return null;
    }

    @Override
    public void addComment(CommentDTO dto) {

    }

    @Override
    public void deleteComment(Long id) {

    }

}
