package com.jobhacker.productservice.model.dto;

import com.jobhacker.productservice.model.entity.Brand;
import com.jobhacker.productservice.model.entity.Category;
import com.jobhacker.productservice.util.ProductStatus;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String productName;
    private String productDescription;
    private ProductStatus productStatus = ProductStatus.ACTIVE;
    private String imagePath;
    private Double price;
    private String color;
    private String material;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Category category;
}
