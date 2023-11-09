package com.jobhacker.productservice.service;

import com.jobhacker.productservice.dao.CategoryDAO;
import com.jobhacker.productservice.exception.UnExpectedException;
import com.jobhacker.productservice.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        try {
            return categoryDAO.save(categoryDTO);
        } catch (Exception e) {
            throw new UnExpectedException("Save Category process was failed");
        }
    }

    public CategoryDTO getCategory(Long id) {
        try {
            return categoryDAO.findById(id);
        } catch (Exception e) {
            throw new UnExpectedException("Get Category with id:%d process was failed".formatted(id));
        }
    }

    public List<CategoryDTO> getAllCategories() {
        try {
            return categoryDAO.findAllCategories();
        } catch (Exception e) {
            throw new UnExpectedException("Get all Category process was failed");
        }
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        try {
            return categoryDAO.updateCategory(id, categoryDTO);
        } catch (Exception e) {
            throw new UnExpectedException("Update Category with id:%d process was failed".formatted(id));
        }
    }

    public void deleteCategory(Long id) {
        try {
            categoryDAO.removeCategoryById(id);
        } catch (Exception e) {
            throw new UnExpectedException("Delete Category with id:%d process was failed".formatted(id));
        }
    }
}
