package com.microservice.repository;

import com.microservice.model.StockModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockRepository extends MongoRepository<StockModel, Long> {

    Optional<StockModel> findByEan(String ean);

    void deleteByEan(String ean);
}
