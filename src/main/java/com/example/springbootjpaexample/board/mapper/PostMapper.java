package com.example.springbootjpaexample.board.mapper;

import com.example.springbootjpaexample.board.dto.PostDTO;
import com.example.springbootjpaexample.board.entity.Post;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO toDto(Post entity);
    List<PostDTO> toDtoList(List<Post> entities);
    Post toEntity(PostDTO dto);
}
