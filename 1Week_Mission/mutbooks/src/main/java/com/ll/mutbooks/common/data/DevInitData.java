package com.ll.mutbooks.common.data;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.entity.MemberRole;
import com.ll.mutbooks.member.service.MemberService;
import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    public CommandLineRunner initData(MemberService memberService, PostService postService, PasswordEncoder passwordEncoder) {
        return args -> {
            Member member1 = memberService.joinMember(Member.builder()
                    .username("T1 Faker")
                    .password(passwordEncoder.encode("1234"))
                    .email("t1_faker@naver.com")
                    .authLevel(MemberRole.USER).build());

            Member member2 = memberService.joinMember(Member.builder()
                    .username("DK Nuguri")
                    .password(passwordEncoder.encode("1234"))
                    .email("dk_nuguri@naver.com")
                    .authLevel(MemberRole.ADMIN).build());

            postService.save(Post.builder()
                    .subject("T1 vs RNG 스코어 예측")
                    .content("# 티원이 3:0으로 이김")
                    .contentHTML("<h1>티원이 3:0으로 이김<h1>")
                    .member(member1).build());

            postService.save(Post.builder()
                    .subject("GEN vs DK 스코어 예측")
                    .content("# 담원이 3:2로 이김")
                    .contentHTML("<h1>담원이 3:2으로 이김<h1>")
                    .member(member2).build());

        };
    }
}
