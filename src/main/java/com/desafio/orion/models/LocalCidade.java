package com.desafio.orion.models;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "local_cidade")
public class LocalCidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @OneToOne(mappedBy = "localCidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Sku sku;

    private long unixtime;

    private String local;

    private String cidade;


}
