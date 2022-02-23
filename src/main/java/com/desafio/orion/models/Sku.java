package com.desafio.orion.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sku")
public class Sku {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String sku;

    @OneToOne
    @JoinColumn(name = "local_cidade_id")
    private  LocalCidade localCidade;
}
