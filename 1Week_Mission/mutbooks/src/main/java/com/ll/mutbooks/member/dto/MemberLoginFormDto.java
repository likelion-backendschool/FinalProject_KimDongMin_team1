package com.ll.mutbooks.member.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MemberLoginFormDto {

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
