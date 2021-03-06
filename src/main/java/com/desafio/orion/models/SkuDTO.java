package com.desafio.orion.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuDTO {

    private long id;

    //USA,MEX,CHI

    private String distribuidora;

    //EN,ES

    private String lingua;

    //SG(single-game),MG(Multi-Games)

    private String contrato;


    private String porcentagem;



    private int quantidadePlacas;

    private int numeroJogos;

    // "Halloween", "Valentine's day", "Easter Sunday", "New Year", "Lunar New Year", "Thanksgiving", "Día de Muertos"

    private List<String> jogos;
    private List<String> newJogos;

    //id do local

    private String local;

    private String cidade;

    //unixtime para o id do local e cidade

    private long unixTime;

    private String skuString;

    private String username;
    private String email;

    private boolean isApproved = false;


}
