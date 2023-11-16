package com.jobhacker.productservice.model.entity;

import com.jobhacker.productservice.util.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;
    private String imagePath;
    private Double price;
    private String color;
    private String material;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;
}
