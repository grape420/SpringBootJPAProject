package com.example.springbootjpaexample.demo.controller;

import com.example.springbootjpaexample.demo.dto.MemberDTO;
import com.example.springbootjpaexample.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public String home(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members";
    }

    @PostMapping("/add")
    public String add(MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "redirect:/member";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "edit-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute MemberDTO dto) {
        memberService.updateMember(dto);
        return "redirect:/member";
    }

}
