package com.microservice.service.impl;

import com.microservice.dto.ProductDto;
import com.microservice.dto.StockDto;
import com.microservice.model.StockModel;
import com.microservice.repository.StockRepository;
import com.microservice.service.StockService;
import com.microservice.util.CommonServiceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultStockService implements StockService {

    private final StockRepository stockRepository;
    private final CommonServiceUtil commonServiceUtil;

    @Override
    public void saveStock(StockDto stockDto) {
        StockModel stockModel = (StockModel)commonServiceUtil.convertToEntity(stockDto);
        stockRepository.save(stockModel);
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<StockModel> stockModelList = stockRepository.findAll();
        List<StockDto> stockDtoList = (List<StockDto>) commonServiceUtil.convertToDto(stockModelList);
        return stockDtoList;
    }

    @Override
    public StockDto getStockByEan(String ean) {
        Optional<StockModel> stock = stockRepository.findByEan(ean);
        if(stock.isPresent()){
            StockDto stockDto = (StockDto)commonServiceUtil.convertToDto(stock.get());
            return stockDto;
        }
        return null;
    }

    @Override
    public void removeStockByEan(String ean) {
        stockRepository.deleteByEan(ean);
    }
}
