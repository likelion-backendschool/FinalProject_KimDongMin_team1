package com.ll.mutbooks.member.service;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
}
