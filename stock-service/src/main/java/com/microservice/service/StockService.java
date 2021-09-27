package com.microservice.service;

import com.microservice.dto.ProductDto;
import com.microservice.dto.StockDto;

import java.util.List;

public interface StockService {

    void saveStock(StockDto stockDto);
    List<StockDto> getAllStocks();
    StockDto getStockByEan(String ean);
    void removeStockByEan(String ean);

}
