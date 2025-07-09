package com.example.springbootjpaexample.board.repository;

import com.example.springbootjpaexample.board.dto.PostDTO;
import com.example.springbootjpaexample.board.entity.Post;
import com.example.springbootjpaexample.board.entity.QPost;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> searchByKeyword(PostDTO postDTO, Pageable pageable) {
        QPost post = QPost.post;

        String searchType = postDTO.getSearchType();
        String keyword = postDTO.getKeyword();
        LocalDate startDate = postDTO.getStartDate();
        LocalDate endDate = postDTO.getEndDate();

        BooleanBuilder builder = new BooleanBuilder();

        switch (searchType) {
            case "title" -> builder.and(post.title.containsIgnoreCase(keyword));
            case "content" -> builder.and(post.content.containsIgnoreCase(keyword));
            case "author" -> builder.and(post.author.containsIgnoreCase(keyword));
            case "all" -> builder.and(
                    post.title.containsIgnoreCase(keyword)
                            .or(post.content.containsIgnoreCase(keyword))
                            .or(post.author.containsIgnoreCase(keyword))
            );
        }

        if (startDate != null) {
            builder.and(post.createdAt.goe(startDate.atStartOfDay()));
        }
        if (endDate != null) {
            builder.and(post.createdAt.loe(endDate.atTime(LocalTime.MAX)));
        }

        List<Post> content = queryFactory
                .selectFrom(post)
                .where(builder)
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(post.count())
                .from(post)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}
