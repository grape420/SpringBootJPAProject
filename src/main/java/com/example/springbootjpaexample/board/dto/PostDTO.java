package com.example.springbootjpaexample.board.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long id;                // 등록 시 null
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    @Builder.Default
    private String keyword = "";

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int pageSize = 10;

    @Builder.Default
    private String searchType = "all";

    private LocalDate startDate;
    private LocalDate endDate;
}
