package com.example.springbootjpaexample.demo.mapper;


import com.example.springbootjpaexample.demo.dto.MemberDTO;
import com.example.springbootjpaexample.demo.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberDTO toDto(Member member);
    Member toEntity(MemberDTO dto);

    List<MemberDTO> toDtoList(List<Member> members);
    List<Member> toEntityList(List<MemberDTO> dtos);
}
