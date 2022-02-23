package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.SkuDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkuControllerTest {

    Utils util = new Utils();

    @Test
    public void testConverterSkuParaSkuDTO() {

    }


    @Test
    public void testConverterSkuDTOParaSku() {

        //"Halloween", "Valentine's day", "Easter Sunday", "New Year"
        // "Lunar New Year", "Thanksgiving", "Día de Muertos"

        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("Halloween");
        jogos.add("Easter Sunday");
        jogos.add("New Year");
        jogos.add("Lunar New Year");
        jogos.add("Valentine's day");
        skuDTO.setDistribuidora("Estados Unidos");
        skuDTO.setLingua("Ingles");
        skuDTO.setContrato("Jogos Multiplos");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(5);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        System.out.println(util.conversorSkuDtoParaSku(skuDTO));

    }


    @Test
    public void testStringSplitAndParseFromSku(){
        String skuString = "USENJM0905HENLV1645580326";
        System.out.println(util.splitStringEvery(skuString));
        String skuString2 = "USENJM0903HLV1645580326";
        System.out.println(util.splitStringEvery(skuString2));
        String skuString3 = "USENJM0902HL1645580326";
        System.out.println(util.splitStringEvery(skuString3 ));
        String skuString4 = "USENJM0901L1645580326";
        System.out.println(util.splitStringEvery(skuString4 ));

        SkuDTO sku1 = util.conversorSkuParaSkuDto(skuString);
        SkuDTO sku2 = util.conversorSkuParaSkuDto(skuString2);
        SkuDTO sku3 = util.conversorSkuParaSkuDto(skuString3);
        SkuDTO sku4 = util.conversorSkuParaSkuDto(skuString4);

        System.out.println(sku1.toString());
        System.out.println(sku2.toString());
        System.out.println(sku3.toString());
        System.out.println(sku4.toString());




    }


}