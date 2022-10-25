package com.ll.mutbooks.member.controller;

import com.ll.mutbooks.common.service.MailService;
import com.ll.mutbooks.member.dto.*;
import com.ll.mutbooks.member.entity.Member;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

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

    @GetMapping("/findUsername")
    public String memberUsernameFindForm(MemberFindFormDto memberFindFormDto) {
        return "member/find_username_form";
    }

    @PostMapping("/findUsername")
    public String memberUsernameFind(
            @Valid MemberFindFormDto memberFindFormDto,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "/member/find_username_form";
        }

        Member member = memberService.findByEmail(memberFindFormDto.getEmail());
        redirectAttributes.addAttribute("username", member != null ? member.getUsername() : null);

        return "redirect:/member/findUsername/success";
    }

    @GetMapping("/findPassword")
    public String memberPasswordFindForm(MemberFindPwdFormDto memberFindPwdFormDto) {
        return "member/find_password_form";
    }

    @PostMapping("/findPassword")
    public String memberPasswordFind(
            @Valid MemberFindPwdFormDto memberFindPwdFormDto,
            BindingResult result, RedirectAttributes redirectAttributes) throws MessagingException {

        if (result.hasErrors()) {
            return "member/find_password_form";
        }

        Member member = memberService.findByUsernameAndEmail(memberFindPwdFormDto.getUsername(), memberFindPwdFormDto.getEmail());
        String temporaryPwd = UUID.randomUUID().toString().replaceAll("-", "");

        if (member != null) {
            memberService.changePassword(member, passwordEncoder.encode(temporaryPwd));
            mailService.sendMail2(member.getEmail(), temporaryPwd);
        }

        redirectAttributes.addAttribute("password", member != null ? temporaryPwd : null);

        return "redirect:/member/findPassword/success";
    }


    @GetMapping("/findUsername/success")
    public String successFindUsername() {
        return "member/find_username";
    }

    @GetMapping("/findPassword/success")
    public String successFindPassword() {
        return "member/find_password";
    }
}
