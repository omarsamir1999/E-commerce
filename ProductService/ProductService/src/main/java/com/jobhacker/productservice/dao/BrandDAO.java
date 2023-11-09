package com.jobhacker.productservice.dao;

import com.jobhacker.productservice.exception.InvalidArgumentException;
import com.jobhacker.productservice.exception.NotFoundException;
import com.jobhacker.productservice.mapper.BrandMapper;
import com.jobhacker.productservice.model.dto.BrandDTO;
import com.jobhacker.productservice.model.entity.Brand;
import com.jobhacker.productservice.model.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandDAO {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Autowired
    public BrandDAO(BrandRepository brandRepository,
                    BrandMapper brandMapper) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }

    public BrandDTO save(BrandDTO brandDTO) {
        try {
            Brand brand = brandMapper.toEntity(brandDTO);
            return brandMapper.toDto(brandRepository.save(brand));
        } catch (Exception e) {
            throw new NotFoundException("Insertion Brand fails, make sure your provide all correct data1");
        }
    }

    public BrandDTO findById(Long id) {
        try {
            return brandMapper.toDto(brandRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new NotFoundException("Brand with id %d Not Exists".formatted(id));
        }
    }


    public Brand findByName(String brandName) {
        try {
            return brandRepository.findByBrandNameEqualsIgnoreCase(brandName).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException("Brand with name '%s' Not Founded".formatted((brandName == null) ? "" : brandName));
        }
    }


    public List<BrandDTO> findAllBrands() {
        try {
            return brandRepository.findAll().stream().map(brandMapper::toDto).toList();
        } catch (Exception e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        try {
            Brand brand = brandRepository.findById(id).orElseThrow();

            brand.setBrandName(brandDTO.getBrandName());
            brand.setBrandDescription(brandDTO.getBrandDescription());
            brand.setCountryOfOrigin(brandDTO.getCountryOfOrigin());

            return brandMapper.toDto(brandRepository.save(brand));
        } catch (Exception e) {
            throw new NotFoundException("Brand with id %d Not Exists".formatted(id));
        }
    }

    public void removeBrandById(Long id) {
        try {
            if (brandRepository.findById(id).isEmpty()) {
                throw new Exception();
            }
            brandRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Brand with id %d Not Exists".formatted(id));
        }
    }
}
