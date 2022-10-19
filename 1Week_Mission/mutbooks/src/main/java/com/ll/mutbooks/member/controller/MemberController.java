package com.ll.mutbooks.member.controller;

import com.ll.mutbooks.common.service.MailService;
import com.ll.mutbooks.member.dto.MemberLoginFormDto;
import com.ll.mutbooks.member.dto.MemberModifyFormDto;
import com.ll.mutbooks.member.dto.MemberModifyPwdDto;
import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.dto.MemberJoinFormDto;
import com.ll.mutbooks.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String memberJoinForm(MemberJoinFormDto memberJoinFormDto) {
        return "member/signup_form";
    }

    @PostMapping("/join")
    @Transactional
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
    public String memberLoginForm(MemberLoginFormDto memberLoginFormDto) {
        return "member/login_form";
    }

    @GetMapping("/modify")
    public String memberModifyForm(Principal principal, Model model) {
        Member findMember = memberService.findByUsername(principal.getName());
        MemberModifyFormDto memberModifyFormDto = new MemberModifyFormDto(findMember.getEmail(), findMember.getNickname());

        model.addAttribute("memberModifyFormDto", memberModifyFormDto);
        return "member/modify_form";
    }

    @PostMapping("/modify")
    @Transactional
    public String memberModify(Principal principal, @Valid MemberModifyFormDto memberModifyFormDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "member/modify_form";
        }

        try {
            Member findMember = memberService.findByUsername(principal.getName());
            findMember.change(memberModifyFormDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/modify_form";
        }

        return "redirect:/";
    }

    @GetMapping("/modifyPassword")
    public String memberModifyPwdForm(MemberModifyPwdDto memberModifyPwdDto) {
        return "member/modify_pwd_form";
    }

    @PostMapping("/modifyPassword")
    public String modifyPwdForm(Principal principal, @Valid MemberModifyPwdDto memberModifyPwdDto, BindingResult result) {
        String oldPassword = memberModifyPwdDto.getOldPassword();
        String password = memberModifyPwdDto.getPassword();
        String passwordConfirm = memberModifyPwdDto.getPasswordConfirm();

        Member findMember = memberService.findByUsername(principal.getName());
        if (passwordEncoder.matches(oldPassword, findMember.getPassword())) {
            if (password.equals(passwordConfirm)) {
                memberService.modifyPassword(principal.getName(), passwordEncoder.encode(password));
            }
        }

        return "redirect:/";
    }
}
