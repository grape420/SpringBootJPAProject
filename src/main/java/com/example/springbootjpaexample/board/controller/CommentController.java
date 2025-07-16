package com.example.springbootjpaexample.board.controller;

import com.example.springbootjpaexample.board.dto.CommentDTO;
import com.example.springbootjpaexample.board.service.CommentService;
import com.example.springbootjpaexample.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    private final PostService postService;

    @PostMapping("/post/{postId}")
    @ResponseBody
    public ResponseEntity<Long> addComment(@RequestBody CommentDTO commentDTO, @PathVariable Long postId) {
        commentDTO.setPostId(postId);
        return ResponseEntity.ok(commentService.addComment(commentDTO));
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{postId}")
    @ResponseBody
    public List<CommentDTO> getCommentByPost(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }
}
