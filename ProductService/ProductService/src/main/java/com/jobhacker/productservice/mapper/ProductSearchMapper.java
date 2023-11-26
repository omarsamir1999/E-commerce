package com.jobhacker.productservice.mapper;

import com.jobhacker.productservice.model.dto.ProductSearchDto;
import com.jobhacker.productservice.model.entity.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductSearchMapper {
    Product toEntity(ProductSearchDto productSearchDto);

    ProductSearchDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductSearchDto productSearchDto, @MappingTarget Product product);
}