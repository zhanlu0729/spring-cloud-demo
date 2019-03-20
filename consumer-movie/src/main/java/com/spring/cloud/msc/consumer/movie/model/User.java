package com.spring.cloud.msc.consumer.movie.model;

import java.math.BigDecimal;

@lombok.Getter
@lombok.Setter
public class User {

    private Long id;

    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}
