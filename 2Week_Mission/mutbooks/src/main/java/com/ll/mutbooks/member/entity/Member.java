package com.ll.mutbooks.member.entity;

import com.ll.mutbooks.common.entity.BaseEntity;
import com.ll.mutbooks.member.dto.MemberJoinFormDto;
import com.ll.mutbooks.member.dto.MemberModifyFormDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_username", unique = true)
    private String username;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_nickname", unique = true)
    private String nickname;

    @Column(name = "member_email", unique = true)
    private String email;

    @Column(name = "member_auth_level")
    @Enumerated(EnumType.STRING)
    private MemberRole authLevel;

    @Builder
    public Member(String username, String password, String nickname, String email, MemberRole authLevel) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.authLevel = authLevel;
    }

    public void change(MemberModifyFormDto memberModifyFormDto) {
        if (!memberModifyFormDto.getNickname().equals("")) {
            this.authLevel = MemberRole.AUTHOR;
        }
        this.email = memberModifyFormDto.getEmail();
        this.nickname = memberModifyFormDto.getNickname();
    }

    public void changePassword(String temporary) {
        this.password = temporary;
    }

    public static Member createMember(MemberJoinFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        MemberRole memberRole = memberFormDto.getNickname().equals("") ? MemberRole.USER : MemberRole.AUTHOR;

        return Member.builder()
                .username(memberFormDto.getUsername())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .nickname(memberFormDto.getNickname())
                .email(memberFormDto.getEmail())
                .authLevel(memberRole)
                .build();
    }

}
