package com.desafio.orion.models;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Sku")
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String skuString;

    @OneToOne
    @MapsId
    private  LocalCidade localCidade;

    private Boolean isApproved = false;
}
