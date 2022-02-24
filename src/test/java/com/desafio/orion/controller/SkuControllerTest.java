package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.LocalCidade;
import com.desafio.orion.models.Sku;
import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.services.SkuServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class SkuControllerTest {

    Utils util = new Utils();
    private final SkuServiceImp skuServiceImp;

    @Autowired
    SkuControllerTest(SkuServiceImp skuServiceImp) {
        this.skuServiceImp = skuServiceImp;
    }


    @Test
    public void testConverterSkuDTOParaSku() {

        //"Halloween", "Valentine's day", "Easter Sunday", "New Year"
        // "Lunar New Year", "Thanksgiving", "Día de Muertos"

        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("H");
        jogos.add("L");
        jogos.add("V");
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        System.out.println(util.conversorSkuDtoParaSku(skuDTO));

    }


    @Test
    public void testStringSplitAndParseFromSku() {
        String skuString = "USENJM0905HENLV1645580326";
        System.out.println(util.splitStringEvery(skuString));
        String skuString2 = "USENJM0903HLV1645621529";
        System.out.println(util.splitStringEvery(skuString2));
        String skuString3 = "CHESUJ0301T1645621529";
        System.out.println(util.splitStringEvery(skuString3));
        String skuString4 = "MXESJM0402NV1645621529";
        System.out.println(util.splitStringEvery(skuString4));

        SkuDTO sku1 = util.conversorSkuParaSkuDto(skuString);
        SkuDTO sku2 = util.conversorSkuParaSkuDto(skuString2);
        SkuDTO sku3 = util.conversorSkuParaSkuDto(skuString3);
        SkuDTO sku4 = util.conversorSkuParaSkuDto(skuString4);

        System.out.println(sku1.toString());
        System.out.println(sku2.toString());
        System.out.println(sku3.toString());
        System.out.println(sku4.toString());


    }

    @Test
    public void testSalvarBD() {


        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("H");
        jogos.add("L");
        jogos.add("V");
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        System.out.println(util.conversorSkuDtoParaSku(skuDTO));

        skuServiceImp.salvarNovoSku(skuDTO);

    }


    @Test
    public void findByUnix(){


        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("H");
        jogos.add("L");
        jogos.add("V");
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        long unixsearch = skuDTO.getUnixTime();
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));

        skuServiceImp.salvarNovoSku(skuDTO);

        LocalCidade localCidade = skuServiceImp.getLocalCidadeByUnix((unixsearch));
        Sku sku = skuServiceImp.findBySku(util.conversorSkuDtoParaSku(skuDTO));
        SkuDTO dbDTO = skuServiceImp.dbToDTO(sku);
        System.out.println(localCidade.toString());

    }


    @Test
    public void testListaSku(){
        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("H");
        jogos.add("L");
        jogos.add("V");
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        System.out.println(util.conversorSkuDtoParaSku(skuDTO));
        skuServiceImp.salvarNovoSku(skuDTO);


        SkuDTO skuDTO2 = new SkuDTO();
        List<String> jogos2 = new ArrayList<>();
        jogos2.add("N");
        jogos2.add("V");
        skuDTO2.setDistribuidora("MX");
        skuDTO2.setLingua("ES");
        skuDTO2.setContrato("MJ");
        skuDTO2.setPorcentagem("30%");
        skuDTO2.setQuantidadePlacas(4);
        skuDTO2.setNumeroJogos(2);
        skuDTO2.setJogos(jogos2);
        skuDTO2.setLocal("Rua porto alegre n399");
        skuDTO2.setCidade("londrina");
        skuDTO2.setUnixTime(Instant.now().getEpochSecond());
        skuDTO2.setSkuString(util.conversorSkuDtoParaSku(skuDTO2));
        System.out.println(util.conversorSkuDtoParaSku(skuDTO2));
        skuServiceImp.salvarNovoSku(skuDTO2);

        SkuDTO skuDTO3 = new SkuDTO();
        List<String> jogos3 = new ArrayList<>();
        jogos3.add("T");
        skuDTO3.setDistribuidora("CH");
        skuDTO3.setLingua("ES");
        skuDTO3.setContrato("UJ");
        skuDTO3.setPorcentagem("10%");
        skuDTO3.setQuantidadePlacas(3);
        skuDTO3.setNumeroJogos(1);
        skuDTO3.setJogos(jogos3);
        skuDTO3.setLocal("Rua natal n299");
        skuDTO3.setCidade("Natal");
        skuDTO3.setUnixTime(Instant.now().getEpochSecond());
        skuDTO3.setSkuString(util.conversorSkuDtoParaSku(skuDTO3));
        System.out.println(util.conversorSkuDtoParaSku(skuDTO3));
        skuServiceImp.salvarNovoSku(skuDTO3);

    /*    List<Sku> lista = skuServiceImp.findAll();
        List<SkuDTO> listaDto = new ArrayList<>();
        for(Sku dto: lista){
            listaDto.add(skuServiceImp.dbToDTO(dto));
        }*/

        List<SkuDTO> listaDto = skuServiceImp.findAlltoDTO();

        System.out.println(listaDto);

    }

}