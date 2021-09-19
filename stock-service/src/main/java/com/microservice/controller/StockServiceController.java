package com.microservice.controller;

import com.microservice.model.StockModel;
import com.microservice.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.microservice.constant.StockServiceConstant.*;

@RestController
@RequestMapping("api/stock")
@AllArgsConstructor
public class StockServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(StockServiceController.class);

    private StockRepository stockRepository;

    @GetMapping("/create")
    public ResponseEntity createStock(@RequestBody StockModel stockModel){
        try{
            stockRepository.save(stockModel);
            return new ResponseEntity<>(stockModel, HttpStatus.CREATED);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return new ResponseEntity<>(stockModel, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StockModel>> getAllStock(){
        try{
            List<StockModel> stockModelList = stockRepository.findAll();
            return new ResponseEntity<>(stockModelList, HttpStatus.FOUND);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getStock/{ean}")
    public ResponseEntity<Optional<StockModel>> getStockById(@PathVariable String ean){
        try{
            Optional<StockModel> stock = stockRepository.findByEan(ean);
            if(stock.isPresent()){
                return new ResponseEntity(stock.get() , HttpStatus.FOUND);
            }
            return new ResponseEntity(stock.get() , HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{ean}")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable String ean ){
        try {
            stockRepository.deleteByEan(ean);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception ex){
            LOG.info(ERROR_OCCURED_MESSAGE + ex);
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

}
