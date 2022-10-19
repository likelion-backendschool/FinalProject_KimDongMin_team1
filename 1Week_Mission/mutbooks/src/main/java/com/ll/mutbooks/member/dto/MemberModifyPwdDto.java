package com.ll.mutbooks.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class MemberModifyPwdDto {
    private String oldPassword;

    private String password;
    private String passwordConfirm;

    public MemberModifyPwdDto(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
