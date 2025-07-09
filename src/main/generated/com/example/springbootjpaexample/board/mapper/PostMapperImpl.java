package com.example.springbootjpaexample.board.mapper;

import com.example.springbootjpaexample.board.dto.PostDTO;
import com.example.springbootjpaexample.board.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-04T11:39:41+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.2.jar, environment: Java 17.0.15 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDTO toDto(Post entity) {
        if ( entity == null ) {
            return null;
        }

        PostDTO.PostDTOBuilder postDTO = PostDTO.builder();

        postDTO.id( entity.getId() );
        postDTO.title( entity.getTitle() );
        postDTO.content( entity.getContent() );
        postDTO.author( entity.getAuthor() );
        postDTO.createdAt( entity.getCreatedAt() );

        return postDTO.build();
    }

    @Override
    public List<PostDTO> toDtoList(List<Post> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PostDTO> list = new ArrayList<PostDTO>( entities.size() );
        for ( Post post : entities ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public Post toEntity(PostDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.id( dto.getId() );
        post.title( dto.getTitle() );
        post.content( dto.getContent() );
        post.author( dto.getAuthor() );
        post.createdAt( dto.getCreatedAt() );

        return post.build();
    }
}
