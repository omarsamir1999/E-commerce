package com.jobhacker.productservice.service;

import com.jobhacker.productservice.dao.BrandDAO;
import com.jobhacker.productservice.exception.UnExpectedException;
import com.jobhacker.productservice.model.dto.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandDAO brandDAO;

    public BrandService(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    public BrandDTO saveBrand(BrandDTO brandDTO) {
        try {
            return brandDAO.save(brandDTO);
        } catch (Exception e) {
            throw new UnExpectedException("Save Brand process was failed");
        }
    }

    public BrandDTO getBrand(Long id) {
        try {
            return brandDAO.findById(id);
        } catch (Exception e) {
            throw new UnExpectedException("Get Brand process was failed");
        }
    }

    public List<BrandDTO> getAllBrands() {
        try {
            return brandDAO.findAllBrands();
        } catch (Exception e) {
            throw new UnExpectedException("Get All Brands process was failed");
        }
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        try {
            return brandDAO.updateBrand(id, brandDTO);
        } catch (Exception e) {
            throw new UnExpectedException("Update Brand with id:%d process was failed".formatted(id));
        }
    }

    public void deleteBrand(Long id) {
        try {
            brandDAO.removeBrandById(id);
        } catch (Exception e) {
            throw new UnExpectedException("Delete Brand with id:%d process was failed".formatted(id));
        }
    }
}
