package com.jobhacker.productservice.dao;

import com.jobhacker.productservice.exception.InvalidArgumentException;
import com.jobhacker.productservice.exception.NotFoundException;
import com.jobhacker.productservice.mapper.ProductMapper;
import com.jobhacker.productservice.model.dto.OrderTotalPriceRequest;
import com.jobhacker.productservice.model.dto.ProductDto;
import com.jobhacker.productservice.model.dto.ProductDtoRequest;
import com.jobhacker.productservice.model.entity.Brand;
import com.jobhacker.productservice.model.entity.Category;
import com.jobhacker.productservice.model.entity.Product;
import com.jobhacker.productservice.model.repository.ProductRepository;
import com.jobhacker.productservice.util.ProductDtoMapper;
import com.jobhacker.productservice.util.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductDAO {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private final BrandDAO brandDAO;
    private final CategoryDAO categoryDAO;
    private final ProductDtoMapper productDtoMapper;

    @Autowired
    public ProductDAO(ProductRepository productRepository,
                      ProductMapper productMapper,
                      BrandDAO brandDAO,
                      CategoryDAO categoryDAO,
                      ProductDtoMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.brandDAO = brandDAO;
        this.categoryDAO = categoryDAO;
        this.productDtoMapper = productDtoMapper;
    }

    public ProductDto save(ProductDtoRequest productDtoRequest) {
        try {
            Brand brand = brandDAO.findByName(productDtoRequest.getBrand());
            Category category = categoryDAO.getCategoryByName(productDtoRequest.getCategory());
            ProductDto productDto = productDtoMapper.toDtoImpl(productDtoRequest);
            productDto.setBrand(brand);
            productDto.setCategory(category);
            Product product = productMapper.toEntity(productDto);
            return productMapper.toDto(productRepository.save(product));
        } catch (Exception e) {
            throw new NotFoundException("Insertion Product fails, make sure your provide all correct data1");
        }
    }

    public ProductDto findById(Long id) {
        try {
            return productMapper.toDto(productRepository.findById(id).orElseThrow());
        } catch (Exception e) {
            throw new NotFoundException("Product with id %d Not Exists".formatted(id));
        }
    }

    public List<ProductDto> findAllProducts() {
        try {
            return productRepository.findAll().stream().map(productMapper::toDto)
                    .filter(product -> product.getProductStatus() == ProductStatus.ACTIVE).toList();
        } catch (Exception e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    public ProductDto updateProduct(Long id, ProductDtoRequest productDtoRequest) {
        try {
            Product product = productRepository.findById(id).orElseThrow();

            Brand brand = brandDAO.findByName(productDtoRequest.getBrand());
            Category category = categoryDAO.getCategoryByName(productDtoRequest.getCategory());

            product.setProductName(productDtoRequest.getProductName());
            product.setProductDescription(productDtoRequest.getProductDescription());
            product.setProductStatus(productDtoRequest.getProductStatus());
            product.setImagePath(productDtoRequest.getImagePath());
            product.setColor(productDtoRequest.getColor());
            product.setPrice(productDtoRequest.getPrice());
            product.setMaterial(productDtoRequest.getMaterial());
            product.setBrand(brand);
            product.setCategory(category);

            return productMapper.toDto(productRepository.save(product));
        } catch (Exception e) {
            throw new NotFoundException("Product with id %d Not Exists".formatted(id));
        }
    }

    // Soft Delete
    public void removeProductById(Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow();

            product.setProductStatus(ProductStatus.valueOf("INACTIVE"));
            productRepository.save(product);
        } catch (Exception e) {
            throw new NotFoundException("Product with id %d Not Exists".formatted(id));
        }
    }

    public Double getTotalCostOfProductList(OrderTotalPriceRequest orderTotalPriceRequest) {

        try {

            return orderTotalPriceRequest.getOrderItemDtoList().stream()
                    .map(e -> productRepository.findById(e.getProductId())
                            .orElseThrow(() -> new NoSuchElementException(e.getProductId().toString()))
                            .getPrice() * e.getQuantity()
                    )
                    .reduce(Double::sum).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Their Product id:%s is not exists!".formatted(e.getMessage()));
        }
    }

    public List<ProductDto> searchAboutProductName(String name) {
        try {
            return productRepository.findByProductNameContainingIgnoreCase(name)
                    .stream()
                    .map(productMapper::toDto)
                    .filter(e -> e.getProductStatus() == ProductStatus.ACTIVE)
                    .toList();
        } catch (Exception ex) {
            throw new NotFoundException("Theirs is no product with this name");
        }
    }
}
