package com.example.springbootjpaexample.board.repository;

import com.example.springbootjpaexample.board.dto.PostDTO;
import com.example.springbootjpaexample.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> searchByKeyword(PostDTO postDTO, Pageable pageable);
}
