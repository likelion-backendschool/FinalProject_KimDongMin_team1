package com.ll.mutbooks.product.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ProductCreateFormDto {

    private String keyword;
    private String subject;
    private Integer price;
    private String productTagContents;
}
