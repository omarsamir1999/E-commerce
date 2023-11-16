package com.jobhacker.productservice.mapper;

import com.jobhacker.productservice.model.dto.BrandDTO;
import com.jobhacker.productservice.model.entity.Brand;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {
    Brand toEntity(BrandDTO brandDTO);

    BrandDTO toDto(Brand brand);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Brand partialUpdate(BrandDTO brandDTO, @MappingTarget Brand brand);
}