package com.ll.mutbooks.member.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MemberFindFormDto {

    @NotEmpty(message = "이메일은 필수 입력값 입니다.")
    private String email;
}
