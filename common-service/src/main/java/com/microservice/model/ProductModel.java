package com.microservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "product")
public class ProductModel extends BaseModel{

    private String ean;
    private String name;
    private String manufacturerName;
    private Double basePrice;

}
