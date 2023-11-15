package com.jobhacker.productservice.controller;

import com.jobhacker.productservice.model.dto.OrderTotalPriceRequest;
import com.jobhacker.productservice.model.dto.OrderTotalPriceResponse;
import com.jobhacker.productservice.model.dto.ProductDto;
import com.jobhacker.productservice.model.dto.ProductDtoRequest;
import com.jobhacker.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        ProductDto result = productService.saveProduct(productDtoRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(name = "id") Long id) {
        ProductDto productDto = productService.getProduct(id);
        return new ResponseEntity<>(productDto, HttpStatus.FOUND);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(value = "name", required = false) String name) {

        List<ProductDto> resultList;
        if (name == null) {
            // Get all Products
            resultList = productService.getAllProducts();
        } else {
            // Search about products
            resultList = productService.searchAboutProducts(name);
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }


    /*

    Delete it because causes ambiguatiy.
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    */


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") Long
                                                            id, @RequestBody ProductDtoRequest productDtoRequest) {
        ProductDto updatedProduct = productService.updateProduct(id, productDtoRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping(value = {"/totalcost"})
    public ResponseEntity<OrderTotalPriceResponse> getTotalPriceOfOrder(
            @RequestBody OrderTotalPriceRequest orderTotalPriceRequest) {
        OrderTotalPriceResponse totalPrice = productService.getAllCost(orderTotalPriceRequest);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
