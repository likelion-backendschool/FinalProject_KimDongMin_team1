package com.ll.mutbooks.post.controller;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.service.MemberService;
import com.ll.mutbooks.post.dto.PostDetailFormDto;
import com.ll.mutbooks.post.dto.PostFormDto;
import com.ll.mutbooks.post.dto.PostUpdateFormDto;
import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.entity.PostHashTag;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.repository.PostHashTagRepository;
import com.ll.mutbooks.post.repository.PostKeywordRepository;
import com.ll.mutbooks.post.service.PostHashTagService;
import com.ll.mutbooks.post.service.PostKeywordService;
import com.ll.mutbooks.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
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
    public String postWriteForm(PostFormDto postFormDto) {
        return "post/post_write_form";
    }

    @PostMapping("/write")
    public String postWrite(Principal principal,
                            @Valid PostFormDto postFormDto,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "post/post_write_form";
        }

        Member member = memberService.findByUsername(principal.getName());
        Post post = Post.createPost(postFormDto, member);
        postService.save(post);

        String keywords = postFormDto.getKeywords();
        PostKeyword postKeyword = PostKeyword.createPostKeyword(keywords);
        postKeywordService.save(postKeyword);

        PostHashTag postHashTag = PostHashTag.createPostHashTag(member, post, postKeyword);
        postHashTagService.save(postHashTag);

        return "redirect:/post/list";
    }

    @GetMapping("/{id}")
    public String postDetailForm(@PathVariable("id") Long postId, Model model) {
        Post post = postService.findById(postId);
        PostHashTag postHashTag = postHashTagService.findAllByPostId(post.getId());

        if (postHashTag != null) {
            String tags = postHashTag.getPostKeyword().getContent();
            model.addAttribute("tags", tags);
        }
        model.addAttribute("post", post);
        return "post/post_detail_form";
    }

    @GetMapping("/{id}/modify")
    public String postModifyForm(@PathVariable("id") Long postId, Model model) {
        Post post = postService.findById(postId);
        PostHashTag postHashTag = postHashTagService.findAllByPostId(postId);
        String tags;
        if(postHashTag == null) {
            tags = "";
        } else {
            tags = postHashTag.getPostKeyword().getContent();
        }

        PostUpdateFormDto postUpdateFormDto = PostUpdateFormDto.builder()
                .subject(post.getSubject())
                .content(post.getContent())
                .keywords(tags)
                .build();

        model.addAttribute("postId", post.getId());
        model.addAttribute("postUpdateFormDto", postUpdateFormDto);
        return "post/post_update_form";
    }

    @PostMapping("/{id}/modify")
    public String postUpdate(@PathVariable("id") Long postId,
                             @Valid PostUpdateFormDto postUpdateFormDto,
                             BindingResult result) {

        if(result.hasErrors()) {
            return "post/post_update_form";
        }

        Post post = postService.findById(postId);
        PostHashTag postHashTag = postHashTagService.findAllByPostId(postId);

        post.setSubject(postUpdateFormDto.getSubject());
        post.setContent(postUpdateFormDto.getContent());
        post.setContentHTML(postUpdateFormDto.getContentHTML());

        if(postHashTag == null) { // 해시태그가 없는 게시글인 경우에는 create
            String keywords = postUpdateFormDto.getKeywords();
            PostKeyword postKeyword = PostKeyword.createPostKeyword(keywords);
            postKeywordService.save(postKeyword);

            postHashTagService.save(PostHashTag.createPostHashTag(post.getMember(), post, postKeyword));
        } else { // 기존 해시태그가 있는 경우에는 Dirty Checking으로 update
            postHashTagService.changeHashTag(postHashTag, postUpdateFormDto.getKeywords());
        }

        return "redirect:/post/%d".formatted(postId);
    }

    @GetMapping("/{id}/delete")
    public String postDelete(@PathVariable("id") Long postId) {
        PostHashTag postHashTag = postHashTagService.findAllByPostId(postId);
        Long postHashTagId = postHashTag.getId();
        Long postKeywordId = postHashTag.getPostKeyword().getId();

        postHashTagService.deletePostHashTag(postHashTagId);
        postKeywordService.deletePostKeyword(postKeywordId);
        Post post = postService.findById(postId);
        postService.postDelete(post);

        return "redirect:/post/list";
    }
}