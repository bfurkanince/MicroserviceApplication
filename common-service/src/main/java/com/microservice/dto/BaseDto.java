package com.microservice.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {

    private Long id;
    private Date createdAt;
    private Date updatedAt;

}
