package com.ll.mutbooks.post.service;

import com.ll.mutbooks.post.entity.PostHashTag;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.repository.PostKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostKeywordService {

    private final PostKeywordRepository postKeywordRepository;

    public PostKeyword save(PostKeyword postKeyword) {
        return postKeywordRepository.save(postKeyword);
    }

    public PostKeyword findPostKeyword(Long id) {
        return postKeywordRepository.findById(id).orElse(null);
    }

    public List<PostKeyword> findAllByAuthorId(Long authorId) {
        return postKeywordRepository.findAllByAuthorId(authorId);
    }

    public void deletePostKeyword(Long id) {
        postKeywordRepository.deleteById(id);
    }
}
