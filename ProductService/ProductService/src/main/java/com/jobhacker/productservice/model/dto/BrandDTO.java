package com.jobhacker.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long id;
    private String brandName;
    private String brandDescription;
    private String countryOfOrigin;
//    private List<Product> products;
}
