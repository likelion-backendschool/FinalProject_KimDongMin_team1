package com.ll.mutbooks.post.entity;

import com.ll.mutbooks.common.entity.BaseEntity;
import com.ll.mutbooks.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_hashtag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_keyword_id")
    private PostKeyword postKeyword;

    public static PostHashTag createPostHashTag(Member member, Post post, PostKeyword postKeyword) {
        return PostHashTag.builder()
                .member(member)
                .post(post)
                .postKeyword(postKeyword)
                .build();
    }

}
