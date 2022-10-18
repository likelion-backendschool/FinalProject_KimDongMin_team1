package com.ll.mutbooks.member.service;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member joinMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 중복 회원 검사
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }
}
