package com.microservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto extends BaseDto {
    private String ean;
    private String name;
    private String manufacturerName;
    private Double basePrice;
}
