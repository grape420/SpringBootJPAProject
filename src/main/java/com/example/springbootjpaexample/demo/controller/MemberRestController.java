package com.example.springbootjpaexample.demo.controller;

import com.example.springbootjpaexample.demo.dto.MemberDTO;
import com.example.springbootjpaexample.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    // 등록 (POST)
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody MemberDTO dto) {
        Long id = memberService.save(dto);
        return ResponseEntity.ok(id);
    }

    // 단건 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    // 전체 조회 (GET)
    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody MemberDTO dto) {
        dto.setId(id);
        memberService.updateMember(dto);
        return ResponseEntity.ok().build(); // 성공했지만 응답으로 줄 데이터가 없을 때 사용
    }

    // 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

