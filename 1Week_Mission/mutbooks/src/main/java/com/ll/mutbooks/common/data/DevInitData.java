package com.ll.mutbooks.common.data;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.entity.MemberRole;
import com.ll.mutbooks.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    public CommandLineRunner initData(MemberService memberService, PasswordEncoder passwordEncoder) {
        return args -> {
            memberService.joinMember(Member.builder()
                    .username("user1")
                    .password(passwordEncoder.encode("1234"))
                    .email("user1@naver.com")
                    .authLevel(MemberRole.USER).build());

            memberService.joinMember(Member.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .email("admin@naver.com")
                    .authLevel(MemberRole.ADMIN).build());
        };
    }
}
