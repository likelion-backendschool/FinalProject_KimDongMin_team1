package com.ll.mutbooks.post.repository;

import com.ll.mutbooks.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
