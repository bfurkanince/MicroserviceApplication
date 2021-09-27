package com.microservice.client;

import com.microservice.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "stock-service", url = "http://localhost:2111/")
public interface StockServiceClient {

    @GetMapping("api/stock/getStock/{ean}")
    ResponseEntity<StockDto> getStockByEan(@PathVariable String ean);

}
