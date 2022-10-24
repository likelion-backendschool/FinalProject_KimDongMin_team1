package com.ll.mutbooks.post.service;

import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public void postDelete(Long postId) {
        postRepository.deleteById(postId);
    }
}
