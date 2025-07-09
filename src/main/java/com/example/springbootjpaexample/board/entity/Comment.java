package com.example.springbootjpaexample.board.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글 내용
    @Column(nullable = false, length = 1000)
    private String content;

    // 작성자
    @Column (nullable = false)
    private String author;

    // 댓글 등록 시간
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 게시글과 연관관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
