package com.ll.mutbooks.common.data;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.entity.MemberRole;
import com.ll.mutbooks.member.service.MemberService;
import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.entity.PostHashTag;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.service.PostHashTagService;
import com.ll.mutbooks.post.service.PostKeywordService;
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
    public CommandLineRunner initData(MemberService memberService,
                                      PostService postService,
                                      PostHashTagService postHashTagService,
                                      PostKeywordService postKeywordService,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            Member member1 = memberService.joinMember(Member.builder()
                    .username("user1")
                    .password(passwordEncoder.encode("1234"))
                    .email("ddmkim94@naver.com")
                    .authLevel(MemberRole.USER).build());

            Member member2 = memberService.joinMember(Member.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .email("admin@naver.com")
                    .authLevel(MemberRole.ADMIN).build());

            Post post1 = postService.save(Post.builder()
                    .subject("1주차 리팩토링 목록")
                    .content("### 메일 발송 비동기 방식으로 변경")
                    .contentHTML("<h3>메일 발송 비동기 방식으로 변경<h3>")
                    .member(member1).build());

            Post post2 = postService.save(Post.builder()
                    .subject("2주차 구현 목록")
                    .content("### 상품 결제 기능 구현")
                    .contentHTML("<h3>상품 결제 기능 구현<h3>")
                    .member(member2).build());

            PostKeyword keyword1 = postKeywordService.save(PostKeyword.builder()
                    .content("#1주차 #멋사")
                    .build());

            PostKeyword keyword2 = postKeywordService.save(PostKeyword.builder()
                    .content("#2주차 #멋사")
                    .build());

            PostHashTag tag1 = postHashTagService.save(PostHashTag.builder()
                    .post(post1)
                    .member(member1)
                    .postKeyword(keyword1)
                    .build());

            PostHashTag tag2 = postHashTagService.save(PostHashTag.builder()
                    .post(post2)
                    .member(member2)
                    .postKeyword(keyword2)
                    .build());

        };
    }
}
