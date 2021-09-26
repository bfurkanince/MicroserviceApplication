package com.microservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "stock")
@AllArgsConstructor
@NoArgsConstructor
public class StockModel extends BaseModel{
    private String ean;
    private int amount;
}
