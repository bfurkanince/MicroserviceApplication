package com.microservice.contoller;

import com.microservice.dto.ProductDto;
import com.microservice.model.ProductModel;
import com.microservice.repository.ProductRepository;
import com.microservice.util.CommonServiceUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceController.class);

    private final ProductRepository productRepository;
    private final CommonServiceUtil commonServiceUtil;

    @GetMapping("/create")
    public ResponseEntity createProducts(@RequestBody ProductDto productDto) {
        ProductModel productModel = (ProductModel) commonServiceUtil.convertToEntity(productDto);
        productRepository.save(productModel);
        return new ResponseEntity<>(productModel, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllStock() {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList = (List<ProductDto>) commonServiceUtil.convertToDto(productModelList);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Optional<ProductDto>> getProductById(@PathVariable Long id) {
        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isPresent()) {
            ProductDto productDto = (ProductDto) commonServiceUtil.convertToDto(productModel.get());
            return new ResponseEntity(productDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
