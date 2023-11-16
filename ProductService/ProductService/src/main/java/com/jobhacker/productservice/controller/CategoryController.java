package com.jobhacker.productservice.controller;

import com.jobhacker.productservice.model.dto.CategoryDTO;
import com.jobhacker.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<CategoryDTO> saveBrand(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO result = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getBrand(@PathVariable(name = "id") Long id) {
        CategoryDTO categoryDTO = categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.FOUND);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<CategoryDTO>> getAllBrands() {
        List<CategoryDTO> allBrands = categoryService.getAllCategories();
        return new ResponseEntity<>(allBrands, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateBrand(@PathVariable(name = "id") Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO brand = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBrand(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
    }
}
