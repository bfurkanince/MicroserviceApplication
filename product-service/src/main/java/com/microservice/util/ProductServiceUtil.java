package com.microservice.util;

import com.microservice.dto.ProductDto;
import com.microservice.model.ProductModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductServiceUtil {

    private ModelMapper modelMapper;

    public ProductModel convertToEntity (ProductDto productDto)
    {
        ProductModel productModel = modelMapper.map(productDto, ProductModel.class);
        return productModel;
    }

    public ProductDto convertToDto (ProductModel productModel)
    {
        ProductDto productDto = modelMapper.map(productModel, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> convertToDto (List<ProductModel> productModelList)
    {
        return Arrays.asList(modelMapper.map(productModelList, ProductDto[].class));
    }

}
