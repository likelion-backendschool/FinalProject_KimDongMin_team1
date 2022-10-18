package com.ll.mutbooks.member.repository;

import com.ll.mutbooks.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 회원가입 시 중복된 회원을 검사하기 위한 메서드
    Member findByEmail(String email);

    Optional<Member> findByUsername(String username);
}
