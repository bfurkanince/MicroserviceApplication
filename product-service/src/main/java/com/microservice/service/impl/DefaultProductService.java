package com.microservice.service.impl;

import com.microservice.client.StockServiceClient;
import com.microservice.dto.ProductDto;
import com.microservice.dto.StockDto;
import com.microservice.model.ProductModel;
import com.microservice.repository.ProductRepository;
import com.microservice.service.ProductService;
import com.microservice.util.CommonServiceUtil;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CommonServiceUtil commonServiceUtil;
    private final StockServiceClient stockServiceClient;

    @Override
    public void saveProduct(ProductDto productDto) {
        ProductModel productModel = (ProductModel) commonServiceUtil.convertToEntity(productDto);
        productRepository.save(productModel);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductModel> productModelList = productRepository.findAll();
        List<ProductDto> productDtoList = (List<ProductDto>) commonServiceUtil.convertToDto(productModelList);
        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Optional<ProductModel> productModel = productRepository.findById(id);
        if(productModel.isPresent()){
            ProductDto productDto = (ProductDto) commonServiceUtil.convertToDto(productModel.get());
            try{
                ResponseEntity<StockDto> stockDto = stockServiceClient.getStockByEan(productDto.getEan());
                if(Objects.nonNull(stockDto.getBody())){
                    if(stockDto.getBody().getAmount() == 0){
                        //RabbitMQ ile kuyruga mesaj iletilecek yada mail yollacak!
                    }
                    productDto.setAmount(stockDto.getBody().getAmount());
                }
                return productDto;
            }catch (FeignException.FeignClientException ex){
                return productDto;
            }
        }
        return null;
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
