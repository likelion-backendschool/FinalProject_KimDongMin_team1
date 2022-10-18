package com.ll.mutbooks.member.entity;

import com.ll.mutbooks.common.entity.BaseEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_username")
    private String username;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_auth_level")
    @Enumerated(EnumType.STRING)
    private AuthLevel authLevel;

    @Builder
    public Member(String username, String password, String nickname, String email, AuthLevel authLevel) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.authLevel = authLevel;
    }

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(memberFormDto.getUsername())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .nickname(memberFormDto.getNickname())
                .email(memberFormDto.getEmail())
                .authLevel(memberFormDto.getNickname().equals("") ? AuthLevel.NORMAL : AuthLevel.AUTHOR)
                .build();
    }

}
