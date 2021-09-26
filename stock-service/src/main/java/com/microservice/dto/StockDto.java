package com.microservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Long id;
    private String ean;
    private int amount;
}
