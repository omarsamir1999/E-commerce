package com.jobhacker.productservice.dao;

import com.jobhacker.productservice.exception.InvalidArgumentException;
import com.jobhacker.productservice.exception.NotFoundException;
import com.jobhacker.productservice.mapper.CategoryMapper;
import com.jobhacker.productservice.model.dto.CategoryDTO;
import com.jobhacker.productservice.model.entity.Category;
import com.jobhacker.productservice.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDAO {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryDAO(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        try {

            Category brand = categoryMapper.toEntity(categoryDTO);
            return categoryMapper.toDto(categoryRepository.save(brand));
        } catch (Exception e) {
            throw new NotFoundException("Insertion category fails, make sure your provide all correct data1");
        }
    }

    public CategoryDTO findById(Long id) {
        try {
            return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new NotFoundException("Category with id %d Not Exists".formatted(id));
        }
    }

    public List<CategoryDTO> findAllCategories() {
        try {
            return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
        } catch (Exception e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        try {
            Category brand = categoryRepository.findById(id).orElseThrow();

            brand.setCategoryName(categoryDTO.getCategoryName());
            brand.setCategoryDescription(categoryDTO.getCategoryDescription());

            return categoryMapper.toDto(categoryRepository.save(brand));
        } catch (Exception e) {
            throw new NotFoundException("Category with id %d Not Exists".formatted(id));
        }
    }

    public void removeCategoryById(Long id) {
        try {
            if (categoryRepository.findById(id).isEmpty()) {
                throw new Exception();
            }
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Category with id %d Not Exists".formatted(id));
        }
    }

    public Category getCategoryByName(String categoryName) {
        try {
            return categoryRepository.findByCategoryNameEqualsIgnoreCase(categoryName).orElseThrow();
        } catch (Exception e) {
            throw new NotFoundException("Category with name '%s' not founded".formatted(categoryName));
        }
    }
}
