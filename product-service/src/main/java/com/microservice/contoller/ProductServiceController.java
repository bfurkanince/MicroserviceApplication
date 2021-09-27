package com.microservice.contoller;

import com.microservice.dto.ProductDto;
import com.microservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.microservice.constant.ProductServiceConstant.*;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
@Slf4j
public class ProductServiceController {

    private final ProductService productService;

    @GetMapping("/create")
    public ResponseEntity createProducts(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllStock() {
        List<ProductDto> productDtoList = productService.getAllProducts();
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Optional<ProductDto>> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.getProductById(id);
        if(Objects.nonNull(productDto)){
            return new ResponseEntity(productDto, HttpStatus.OK);
        }
        return new ResponseEntity(PRODUCT_NOT_FOUND_MESSAGE, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.removeProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
