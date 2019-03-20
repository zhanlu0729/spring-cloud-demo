package com.spring.cloud.msc.consumer.movie.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}
