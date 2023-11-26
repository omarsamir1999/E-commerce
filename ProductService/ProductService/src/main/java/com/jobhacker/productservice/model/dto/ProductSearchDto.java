package com.jobhacker.productservice.model.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSearchDto {
    private Long id;
    private String productName;
}
