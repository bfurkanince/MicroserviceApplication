package com.microservice.controller;

import com.microservice.dto.StockDto;
import com.microservice.model.StockModel;
import com.microservice.repository.StockRepository;
import com.microservice.util.CommonServiceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/stock")
@AllArgsConstructor
@Slf4j
public class StockServiceController {

    private final StockRepository stockRepository;
    private final CommonServiceUtil commonServiceUtil;

    @GetMapping("/create")
    public ResponseEntity createStock(@RequestBody StockDto stockDto) {
        StockModel stockModel = (StockModel)commonServiceUtil.convertToEntity(stockDto);
        stockRepository.save(stockModel);
        return new ResponseEntity<>(stockModel, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StockDto>> getAllStock() {
        List<StockModel> stockModelList = stockRepository.findAll();
        List<StockDto> stockDtoList = (List<StockDto>) commonServiceUtil.convertToDto(stockModelList);
        return new ResponseEntity<>(stockDtoList, HttpStatus.OK);
    }

    @GetMapping("/getStock/{ean}")
    public ResponseEntity<Optional<StockDto>> getStockByEan(@PathVariable String ean) {
        Optional<StockModel> stock = stockRepository.findByEan(ean);
        if (stock.isPresent()) {
            StockDto stockDto = (StockDto)commonServiceUtil.convertToDto(stock.get());
            return new ResponseEntity(stockDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{ean}")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable String ean) {
        stockRepository.deleteByEan(ean);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
