package com.ll.mutbooks.post.controller;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.service.MemberService;
import com.ll.mutbooks.post.dto.PostWriteFormDto;
import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.entity.PostHashTag;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.service.PostHashTagService;
import com.ll.mutbooks.post.service.PostKeywordService;
import com.ll.mutbooks.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostHashTagService postHashTagService;
    private final PostKeywordService postKeywordService;
    private final MemberService memberService;


    @GetMapping("/list")
    public String postList(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post/post_list";
    }

    @GetMapping("/write")
    public String writeForm(PostWriteFormDto postWriteFormDto) {
        return "post/write_form";
    }

    @PostMapping("/write")
    public String write(Principal principal, @Valid PostWriteFormDto postWriteFormDto, BindingResult result) {
        if (result.hasErrors()) {
            return "post/write_form";
        }

        Member member = memberService.findByUsername(principal.getName());
        Post post = Post.createPost(postWriteFormDto, member);
        postService.save(post);

        String keywords = postWriteFormDto.getKeywords();
        PostKeyword postKeyword = PostKeyword.createPostKeyword(keywords);
        postKeywordService.save(postKeyword);

        PostHashTag postHashTag = PostHashTag.createPostHashTag(member, post, postKeyword);
        postHashTagService.save(postHashTag);

        return "redirect:/post/list";
    }
}