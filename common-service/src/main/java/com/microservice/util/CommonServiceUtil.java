package com.microservice.util;


import com.microservice.dto.BaseDto;
import com.microservice.dto.ProductDto;
import com.microservice.dto.StockDto;
import com.microservice.model.BaseModel;
import com.microservice.model.ProductModel;
import com.microservice.model.StockModel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class CommonServiceUtil {

    private ModelMapper modelMapper;

    public BaseModel convertToEntity (BaseDto dto)
    {
        if(dto instanceof ProductDto){
            ProductModel productModel = modelMapper.map(dto, ProductModel.class);
            return productModel;
        }
        else if(dto instanceof StockDto){
            StockModel stockModel = modelMapper.map(dto, StockModel.class);
            return stockModel;
        }
        return null;
    }

    public BaseDto convertToDto (BaseModel model)
    {
        if(model instanceof ProductModel){
            ProductDto productDto = modelMapper.map(model, ProductDto.class);
            return productDto;
        }
        else if (model instanceof StockModel){
            StockDto stockDto = modelMapper.map(model, StockDto.class);
            return stockDto;
        }
        return null;
    }

    public List<? extends BaseDto> convertToDto (List<? extends BaseModel> modelList)
    {
        Object object = getTypeOfList(modelList);
        if(object instanceof ProductModel){
            return Arrays.asList(modelMapper.map(modelList, ProductDto[].class));
        }
        else if (object instanceof StockModel){
            return Arrays.asList(modelMapper.map(modelList, StockDto[].class));
        }
        return null;
    }

    private Object getTypeOfList(List<? extends BaseModel> modelList) {
        if(CollectionUtils.isNotEmpty(modelList)){
            return modelList.get(0);
        }
        return new Object();
    }

}
