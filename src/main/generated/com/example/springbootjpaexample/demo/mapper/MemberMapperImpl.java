package com.example.springbootjpaexample.demo.mapper;

import com.example.springbootjpaexample.demo.dto.MemberDTO;
import com.example.springbootjpaexample.demo.entity.Member;
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
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO toDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO.MemberDTOBuilder memberDTO = MemberDTO.builder();

        memberDTO.id( member.getId() );
        memberDTO.name( member.getName() );
        memberDTO.email( member.getEmail() );

        return memberDTO.build();
    }

    @Override
    public Member toEntity(MemberDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.id( dto.getId() );
        member.name( dto.getName() );
        member.email( dto.getEmail() );

        return member.build();
    }

    @Override
    public List<MemberDTO> toDtoList(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDTO> list = new ArrayList<MemberDTO>( members.size() );
        for ( Member member : members ) {
            list.add( toDto( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toEntityList(List<MemberDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( dtos.size() );
        for ( MemberDTO memberDTO : dtos ) {
            list.add( toEntity( memberDTO ) );
        }

        return list;
    }
}
