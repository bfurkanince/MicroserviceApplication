package com.microservice.service;

import com.microservice.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    void removeProduct(Long id);

}
