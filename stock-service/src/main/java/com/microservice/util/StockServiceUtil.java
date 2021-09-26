package com.microservice.util;


import com.microservice.dto.StockDto;
import com.microservice.model.StockModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class StockServiceUtil {

    private ModelMapper modelMapper;

    public StockModel convertToEntity (StockDto stockDto)
    {
        StockModel stockModel = modelMapper.map(stockDto, StockModel.class);
        return stockModel;
    }

    public StockDto convertToDto (StockModel stockModel)
    {
        StockDto stockDto = modelMapper.map(stockModel, StockDto.class);
        return stockDto;
    }

    public List<StockDto> convertToDto (List<StockModel> stockModelList)
    {
        return Arrays.asList(modelMapper.map(stockModelList, StockDto[].class));
    }

}
