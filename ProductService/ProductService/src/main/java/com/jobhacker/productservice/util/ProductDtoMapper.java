package com.jobhacker.productservice.util;

import com.jobhacker.productservice.model.dto.ProductDto;
import com.jobhacker.productservice.model.dto.ProductDtoRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoMapper {

    public ProductDto toDtoImpl(ProductDtoRequest productDtoRequest) {
        if (productDtoRequest == null) {
            return null;
        }
        return ProductDto.builder()
                .productName(productDtoRequest.getProductName())
                .productDescription(productDtoRequest.getProductDescription())
                .imagePath(productDtoRequest.getImagePath())
                .productStatus(productDtoRequest.getProductStatus())
                .color(productDtoRequest.getColor())
                .price(productDtoRequest.getPrice())
                .material(productDtoRequest.getMaterial())
                .build();
    }
}
