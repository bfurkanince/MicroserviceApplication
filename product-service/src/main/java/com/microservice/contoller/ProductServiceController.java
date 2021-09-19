package com.microservice.contoller;

import com.microservice.model.ProductModel;
import com.microservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.microservice.constant.ProductServiceConstant.ERROR_OCCURED_MESSAGE;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceController.class);

    private ProductRepository productRepository;

    @GetMapping("/create")
    public ResponseEntity createProducts(@RequestBody ProductModel productModel){
        try{
            productRepository.save(productModel);
            return new ResponseEntity<>(productModel, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(productModel, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductModel>> getAllStock(){
        try{
            List<ProductModel> stockModelList = productRepository.findAll();
            return new ResponseEntity<>(stockModelList, HttpStatus.FOUND);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Optional<ProductModel>> getProductById(@PathVariable Long id){
        try{
            Optional<ProductModel> stock = productRepository.findById(id);
            if(stock.isPresent()){
                return new ResponseEntity(stock.get() , HttpStatus.FOUND);
            }
            return new ResponseEntity(stock.get() , HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id ){
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }
}
