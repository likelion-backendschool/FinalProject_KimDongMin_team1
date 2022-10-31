package com.ll.mutbooks.product.service;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.service.PostHashTagService;
import com.ll.mutbooks.product.entity.Product;
import com.ll.mutbooks.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final PostHashTagService postHashTagService;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Post> findPostsByProduct(Product product) {
        Member member = product.getMember();
        PostKeyword postKeyword = product.getPostKeyword();
        postHashTagService.getPostTag(member.getId(), postKeyword.getId());

    }
}
