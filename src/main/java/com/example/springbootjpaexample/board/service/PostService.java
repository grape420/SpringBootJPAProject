package com.example.springbootjpaexample.board.service;

import com.example.springbootjpaexample.board.dto.PostDTO;
import com.example.springbootjpaexample.board.entity.Post;
import com.example.springbootjpaexample.board.mapper.PostMapper;
import com.example.springbootjpaexample.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepo;
    private final PostMapper postMapper;

    /* 등록 */
    public Long save(PostDTO dto) {
        Post entity = postMapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        return postRepo.save(entity).getId();
    }

    /* 단건 조회 */
    @Transactional(readOnly = true)
    public PostDTO findById(Long id) {
        return postMapper.toDto(
                postRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("post not found: " + id)));
    }

    /* 수정 */
    public void update(PostDTO dto) {
        Post post = postRepo.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("post not found: " + dto.getId()));
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
    }

    /* 삭제 */
    public void delete(Long id) { postRepo.deleteById(id); }

    /* 전체 조회 */
    @Transactional(readOnly = true)
    public Page<Post> findAllByKeyword(PostDTO postDTO, Pageable pageable) {
        return postRepo.searchByKeyword(postDTO, pageable);
    }

}
