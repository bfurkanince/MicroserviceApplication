package com.microservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private String ean;
    private String name;
    private String manufacturerName;
    private Double basePrice;
}
