package com.microservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockDto extends BaseDto{
    private String ean;
    private int amount;
}
