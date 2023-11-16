package com.jobhacker.productservice.service;

import com.jobhacker.productservice.dao.ProductDAO;
import com.jobhacker.productservice.exception.UnExpectedException;
import com.jobhacker.productservice.model.dto.OrderTotalPriceRequest;
import com.jobhacker.productservice.model.dto.OrderTotalPriceResponse;
import com.jobhacker.productservice.model.dto.ProductDto;
import com.jobhacker.productservice.model.dto.ProductDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ProductDto saveProduct(ProductDtoRequest productDtoRequest) {
        try {
            return productDAO.save(productDtoRequest);
        } catch (Exception e) {
            throw new UnExpectedException("Save Product process was failed");
        }
    }

    public ProductDto getProduct(Long id) {
        try {
            return productDAO.findById(id);
        } catch (Exception e) {
            throw new UnExpectedException("Get Product with id:%d process was failed".formatted(id));
        }
    }

    public List<ProductDto> getAllProducts() {
        try {
            return productDAO.findAllProducts();
        } catch (Exception e) {
            throw new UnExpectedException("Get all Products process was failed");
        }
    }

    public ProductDto updateProduct(Long id, ProductDtoRequest productDtoRequest) {
        try {
            return productDAO.updateProduct(id, productDtoRequest);
        } catch (Exception e) {
            throw new UnExpectedException("Update Product with id:%d process was failed".formatted(id));
        }
    }

    public void deleteProduct(Long id) {
        try {
            productDAO.removeProductById(id);
        } catch (Exception e) {
            throw new UnExpectedException("Delete Product with id:%d process was failed".formatted(id));
        }
    }

    public OrderTotalPriceResponse getAllCost(OrderTotalPriceRequest orderTotalPriceRequest) {
        try {
            Double totalPrice = productDAO.getTotalCostOfProductList(orderTotalPriceRequest);
            return new OrderTotalPriceResponse(totalPrice);
        } catch (Exception e) {
            throw new UnExpectedException("Their exists error in product list!");
        }
    }

    public List<ProductDto> searchAboutProducts(String name) {
        try {
            return productDAO.searchAboutProductName(name.toUpperCase());
        } catch (Exception e) {
            throw new UnExpectedException("Their issue in search about product");
        }
    }
}
