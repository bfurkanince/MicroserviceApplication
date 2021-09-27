package com.microservice.controller;

import com.microservice.dto.StockDto;
import com.microservice.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.microservice.constant.StockServiceConstant.NOT_FOUND_STOCK_FOR_EAN_MESSAGE;

@RestController
@RequestMapping("api/stock")
@AllArgsConstructor
@Slf4j
public class StockServiceController {

    private final StockService stockService;

    @GetMapping("/create")
    public ResponseEntity createStock(@RequestBody StockDto stockDto) {
        stockService.saveStock(stockDto);
        return new ResponseEntity<>(stockDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StockDto>> getAllStock() {
        List<StockDto> stockDtoList = stockService.getAllStocks();
        return new ResponseEntity<>(stockDtoList, HttpStatus.OK);
    }

    @GetMapping("/getStock/{ean}")
    public ResponseEntity<StockDto> getStockByEan(@PathVariable String ean) {
        StockDto stockDto = stockService.getStockByEan(ean);
        if (Objects.nonNull(stockDto)){
            return new ResponseEntity(stockDto, HttpStatus.OK);
        }
        return new ResponseEntity(NOT_FOUND_STOCK_FOR_EAN_MESSAGE , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{ean}")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable String ean) {
        stockService.removeStockByEan(ean);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
