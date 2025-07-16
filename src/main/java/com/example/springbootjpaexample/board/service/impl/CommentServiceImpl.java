package com.example.springbootjpaexample.board.service.impl;

import com.example.springbootjpaexample.board.dto.CommentDTO;
import com.example.springbootjpaexample.board.entity.Comment;
import com.example.springbootjpaexample.board.entity.Post;
import com.example.springbootjpaexample.board.repository.CommentRepository;
import com.example.springbootjpaexample.board.repository.PostRepository;
import com.example.springbootjpaexample.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostIdOrderByIdAsc(postId);
        return comments.stream()
                .map(c -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setId(c.getId());
                    dto.setContent(c.getContent());
                    dto.setAuthor(c.getAuthor());
                    dto.setPostId(postId);
                    dto.setCreatedAt(c.getCreatedAt());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public Long addComment(CommentDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .author(dto.getAuthor())
                .post(post)
                .build();
        Comment result = commentRepository.save(comment);
        return result.getId();
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);

    }

}
