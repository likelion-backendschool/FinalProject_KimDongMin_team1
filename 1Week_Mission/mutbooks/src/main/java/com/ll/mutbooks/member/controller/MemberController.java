package com.ll.mutbooks.member.controller;

import com.ll.mutbooks.common.service.MailService;
import com.ll.mutbooks.member.dto.MemberLoginFormDto;
import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.dto.MemberJoinFormDto;
import com.ll.mutbooks.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String memberJoinForm(Model model) {
        model.addAttribute("memberJoinFormDto", new MemberJoinFormDto());
        return "member/signup_form";
    }

    @PostMapping("/join")
    public String memberJoin(@Valid MemberJoinFormDto memberJoinFormDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "member/signup_form";
        }

        try {
            Member member = Member.createMember(memberJoinFormDto, passwordEncoder);
            memberService.joinMember(member);
            mailService.sendMail(member.getEmail());
        } catch (IllegalStateException | MessagingException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String memberLoginForm(Model model) {
        model.addAttribute("memberLoginFormDto", new MemberLoginFormDto());
        return "member/login_form";
    }
}
