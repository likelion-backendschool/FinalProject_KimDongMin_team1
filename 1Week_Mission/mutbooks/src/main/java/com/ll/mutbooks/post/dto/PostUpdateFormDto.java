package com.ll.mutbooks.post.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PostUpdateFormDto {

    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    private String subject;
    private String content;
    private String contentHTML;
    private String keywords;
}
