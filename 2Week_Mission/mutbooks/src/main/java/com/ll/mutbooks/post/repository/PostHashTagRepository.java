package com.ll.mutbooks.post.repository;

import com.ll.mutbooks.post.entity.PostHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {

    @Query("select phs from PostHashTag phs where phs.post.id = :postId")
    Optional<PostHashTag> findAllByPostId(@Param("postId") Long postId);
}
