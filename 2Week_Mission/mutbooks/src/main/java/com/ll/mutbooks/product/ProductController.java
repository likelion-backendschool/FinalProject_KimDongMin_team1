package com.ll.mutbooks.product;

import com.ll.mutbooks.member.entity.Member;
import com.ll.mutbooks.member.service.MemberService;
import com.ll.mutbooks.post.entity.Post;
import com.ll.mutbooks.post.entity.PostKeyword;
import com.ll.mutbooks.post.service.PostKeywordService;
import com.ll.mutbooks.post.service.PostService;
import com.ll.mutbooks.product.dto.ProductCreateFormDto;
import com.ll.mutbooks.product.entity.Product;
import com.ll.mutbooks.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.SchemaOutputResolver;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final MemberService memberService;
    private final ProductService productService;
    private final PostKeywordService postKeywordService;

    @PreAuthorize("isAuthenticated() and hasAuthority('AUTHOR')")
    @GetMapping("/create")
    public String productCreateForm(@ModelAttribute("form") ProductCreateFormDto productCreateFormDto, Model model, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        // 현재 로그인 한 회원이 쓴 글의 키워드 가져오기
        List<PostKeyword> findKeywords = postKeywordService.findAllByAuthorId(member.getId());

        Set<String> postKeywords = new HashSet<>();
        for (PostKeyword findKeyword : findKeywords) {
            String tag = findKeyword.getContent();
            String s = tag.replaceFirst("#", "").replace(" ", "");

            String[] arr = s.split("#");
            postKeywords.addAll(Arrays.asList(arr));
        }

        model.addAttribute("postKeywords", postKeywords);
        return "product/product_write_form";
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('AUTHOR')")
    @PostMapping("/create")
    public String productCreate(@Valid ProductCreateFormDto form,
                                @RequestParam("postKeywordId") String keyword,
                                Principal principal) {

        Member member = memberService.findByUsername(principal.getName());
        PostKeyword productKeyword = postKeywordService.save(PostKeyword.builder()
                .content(form.getProductTagContents())
                .build());

        Product product = Product.builder()
                .member(member)
                .subject(form.getSubject())
                .price(form.getPrice())
                .postKeyword(productKeyword)
                .build();

        productService.save(product);

        return "redirect:/product/list";
    }


}
