package com.ll.mutbooks.member.service;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member joinMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElse(null);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member findByUsernameAndEmail(String username, String email) {
        return memberRepository.findByUsernameAndEmail(username, email).orElse(null);
    }

    @Transactional
    public int modifyPassword(String username, String newPassword) {
        return memberRepository.modifyPassword(username, newPassword);
    }

    @Transactional
    public void changePassword(Member member, String temporaryPwd) {
        Member findMember = memberRepository.findById(member.getId()).orElse(null);
        findMember.changePassword(temporaryPwd);
    }

    // 중복 회원 검사
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }
}
