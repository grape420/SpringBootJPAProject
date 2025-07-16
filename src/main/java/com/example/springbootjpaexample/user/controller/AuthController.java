package com.example.springbootjpaexample.user.controller;

import com.example.springbootjpaexample.user.entity.User;
import com.example.springbootjpaexample.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String naverRedirectUri;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverClientSecret;

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "index";
    }

    // 로그인 처리
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password,
//                        HttpSession session, Model model) {
//        var userOpt = userService.findByUsername(username);
//        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
//            session.setAttribute("loginUser", userOpt.get());
//            return "redirect:/";
//        } else {
//            model.addAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
//            return "user/login";
//        }
//    }

    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm() {
        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("signupError", "이미 사용 중인 아이디입니다.");
            return "user/signup";
        }
        User user = User.builder()
                .username(username)
                .password(password) // 실무에서는 반드시 암호화!
                .build();
        userService.save(user);
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
        model.addAttribute("user", oAuth2User);
        return "user/profile";
    }

}
