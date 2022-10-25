package com.ll.mutbooks.post.repository;

import com.ll.mutbooks.post.entity.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long> {
    @Query(value = "select *" +
            " from post_keyword keyword" +
            " join post_hash_tag tag" +
            " on (tag.post_keyword_id = keyword.post_keyword_id)" +
            " where tag.member_id = :authorId" +
            " order by tag.post_id desc",
            nativeQuery = true)
    List<PostKeyword> findAllByAuthorId(@Param("authorId") Long authorId);
}
