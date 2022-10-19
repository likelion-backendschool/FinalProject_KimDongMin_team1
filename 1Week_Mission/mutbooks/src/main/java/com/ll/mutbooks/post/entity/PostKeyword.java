package com.ll.mutbooks.post.entity;

import com.ll.mutbooks.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostKeyword extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_keyword_id")
    private Long id;

    @Column(name = "post_keyword_content")
    private String content;

    public static PostKeyword createPostKeyword(String keywords) {
        return PostKeyword.builder()
                .content(keywords)
                .build();
    }
}
