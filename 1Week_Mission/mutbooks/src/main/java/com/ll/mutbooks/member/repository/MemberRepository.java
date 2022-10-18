package com.ll.mutbooks.member.repository;

import com.ll.mutbooks.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
