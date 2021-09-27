package com.microservice.contoller;

import com.microservice.client.StockServiceClient;
import com.microservice.dto.ProductDto;
import com.microservice.dto.StockDto;
import com.microservice.model.ProductModel;
import com.microservice.repository.ProductRepository;
import com.microservice.util.CommonServiceUtil;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.microservice.constant.ProductServiceConstant.*;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
@Slf4j
public class ProductServiceController {

    private final ProductRepository productRepository;
    private final CommonServiceUtil commonServiceUtil;
    private final StockServiceClient stockServiceClient;

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
            try{
                ResponseEntity<Optional<StockDto>> stockDto = stockServiceClient.getStockByEan(productModel.get().getEan());
                if(stockDto.getBody().isPresent()){
                    int amount = stockDto.getBody().get().getAmount();
                    if(amount == 0){
                        //RabitMq ile mail yollayacak bu ean'a sahip product'ın stock'u yok diye!
                    }
                }
                ProductDto productDto = (ProductDto) commonServiceUtil.convertToDto(productModel.get());
                return new ResponseEntity(productDto, HttpStatus.OK);
            }catch (FeignException.FeignClientException ex){
                log.error(ERROR_OCCURED_MESSAGE + ex);
                return new ResponseEntity(PRODUCT_STOCK_NOT_FOUND_MESSAGE , HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity(PRODUCT_NOT_FOUND_MESSAGE , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
