package com.example.springbootjpaexample.demo.service;

import com.example.springbootjpaexample.demo.dto.MemberDTO;
import com.example.springbootjpaexample.demo.entity.Member;
import com.example.springbootjpaexample.demo.mapper.MemberMapper;
import com.example.springbootjpaexample.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    // 등록
    public Long save(MemberDTO dto) {
        Member member = memberMapper.toEntity(dto);
        return memberRepository.save(member).getId();
    }

    // 전체 조회
    public List<MemberDTO> findAll() {
        return memberMapper.toDtoList(memberRepository.findAll());
    }

    // 단건 조회
    public MemberDTO findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 없음 id=" + id));
        return memberMapper.toDto(member);
    }

    // 수정
    @Transactional
    public void updateMember(MemberDTO dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 없음 id=" + dto.getId()));
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
    }

    // 삭제
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 회원 없음 id=" + id);
        }
        memberRepository.deleteById(id);
    }
}
