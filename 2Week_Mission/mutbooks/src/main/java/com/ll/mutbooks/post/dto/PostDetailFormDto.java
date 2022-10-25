package com.ll.mutbooks.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PostDetailFormDto {

    private String subject;
    private String writer;
    private String content;
    private String keywords;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
