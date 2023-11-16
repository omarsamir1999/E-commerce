package com.jobhacker.productservice.model.dto;

import com.jobhacker.productservice.util.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoRequest {
    private Long id;
    private String productName;
    private String productDescription;
    private ProductStatus productStatus = ProductStatus.ACTIVE;
    private String imagePath;
    private Double price;
    private String color;
    private String material;
    private String brand;
    private String category;
}
