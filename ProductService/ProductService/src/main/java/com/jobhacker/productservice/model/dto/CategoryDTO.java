package com.jobhacker.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String categoryName;
    private String categoryDescription;
//    private List<Product> products;
}
