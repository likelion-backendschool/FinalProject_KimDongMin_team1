package com.ll.mutbooks.post.service;

import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.entity.PostHashTag;
import com.ll.mutbooks.post.repository.PostHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostHashTagService {

    private final PostHashTagRepository postHashTagRepository;

    public PostHashTag save(PostHashTag postHashTag) {
        return postHashTagRepository.save(postHashTag);
    }

    public PostHashTag findAllByPostId(Long postId) {
        return postHashTagRepository.findAllByPostId(postId).orElse(null);
    }

    @Transactional
    public void changeHashTag(PostHashTag postHashTag, String keywords) {
        postHashTag.getPostKeyword().setContent(keywords);
    }

    public void deletePostHashTag(Long id) {
        postHashTagRepository.deleteById(id);
    }
}
