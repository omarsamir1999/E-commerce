package com.jobhacker.productservice.controller;

import com.jobhacker.productservice.model.dto.BrandDTO;
import com.jobhacker.productservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<BrandDTO> saveBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO result = brandService.saveBrand(brandDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable(name = "id") Long id) {
        BrandDTO brandDTO = brandService.getBrand(id);
        return new ResponseEntity<>(brandDTO, HttpStatus.FOUND);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> allBrands = brandService.getAllBrands();
        return new ResponseEntity<>(allBrands, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable(name = "id") Long id, @RequestBody BrandDTO brandDTO) {
        BrandDTO brand = brandService.updateBrand(id, brandDTO);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBrand(@PathVariable(name = "id") Long id) {
        brandService.deleteBrand(id);
    }
}
