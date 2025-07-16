package com.example.springbootjpaexample.board.controller;

import com.example.springbootjpaexample.board.dto.PostDTO;
import com.example.springbootjpaexample.board.entity.Post;
import com.example.springbootjpaexample.board.service.CommentService;
import com.example.springbootjpaexample.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    /* 목록 */
    @GetMapping
    public String list(Model model, PostDTO postDTO) {
        model.addAttribute("postDTO", postDTO);
        return "board/posts";
    }

    @GetMapping("/select")
    public String getPosts(PostDTO postDTO, Model model) {
        Pageable pageable = PageRequest.of(
                postDTO.getPage(),
                postDTO.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id")
        );
        Page<Post> posts = postService.findAllByKeyword(postDTO, pageable);

        model.addAttribute("posts", posts);
        return "board/postListAjax :: html";
    }

    /* 상세 */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostDTO postDTO = postService.findById(id);
        model.addAttribute("post", postDTO);

        return "board/post-detail";
    }

    /* 작성 폼 */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("post", new PostDTO());
        return "board/post-form";
    }

    /* 작성 처리 */
    @PostMapping
    public String create(@ModelAttribute PostDTO dto) {
        postService.save(dto);
        return "redirect:/posts";
    }

    /* 수정 폼 */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "board/post-form";
    }

    /* 수정 처리 */
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute PostDTO dto) {
        dto.setId(id);
        postService.update(dto);
        return "redirect:/posts";
    }

    /* 삭제 */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}
