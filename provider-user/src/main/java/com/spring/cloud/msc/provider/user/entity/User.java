package com.spring.cloud.msc.provider.user.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@lombok.Getter
@lombok.Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private BigDecimal balance;
}
