package com.ll.mutbooks.post.service;

import com.ll.mutbooks.post.entity.PostHashTag;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.repository.PostKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostKeywordService {

    private final PostKeywordRepository postKeywordRepository;

    public PostKeyword save(PostKeyword postKeyword) {
        return postKeywordRepository.save(postKeyword);
    }

    public void deletePostKeyword(Long id) {
        postKeywordRepository.deleteById(id);
    }
}
