package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.LocalCidade;
import com.desafio.orion.models.Sku;
import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.services.SkuServiceImp;
import com.desafio.orion.services.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class SkuControllerTest {

    Utils util = new Utils();
    private final SkuServiceImp skuServiceImp;
    private final UserServiceImp userServiceImp;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SkuControllerTest(SkuServiceImp skuServiceImp, UserServiceImp userServiceImp) {
        this.skuServiceImp = skuServiceImp;
        this.userServiceImp = userServiceImp;
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
        //skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuDTO.setUnixTime(1645776697);
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        // SKU esperado USENMJ0903HLV1645776697
        System.out.println(util.conversorSkuDtoParaSku(skuDTO));
        Assertions.assertEquals("USENMJ0903HLV1645776697", skuDTO.getSkuString());
    }


    @Test
    public void testStringSplitAndParseFromSku() {
        String skuString = "USENMJ0905HENLV1645580326";
        List<String> jogos = new ArrayList<>();
        jogos.add("Halloween");
        jogos.add("Easter Sunday");
        jogos.add("New Year");
        jogos.add("Lunar New Year");
        jogos.add("Valentine's day");

        SkuDTO skuDTO = new SkuDTO();
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(5);
        skuDTO.setJogos(jogos);
        skuDTO.setUnixTime(1645580326);

        SkuDTO sku1 = util.conversorSkuParaSkuDto(skuString);

        Assertions.assertEquals(sku1, skuDTO);


    }

    //Teste enviar para DB
    @Test
    public void testSalvarBD() {


        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("Halloween");
        jogos.add("Lunar New Year");
        jogos.add("Día de Muertos");
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUsername("thiagonixi");
        skuDTO.setEmail("thiagonixi@gmail.com");
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        System.out.println(util.conversorSkuDtoParaSku(skuDTO));


        skuServiceImp.salvarNovoSku(skuDTO);

        Sku skuDdb = skuServiceImp.findBySku(skuDTO.getSkuString());

        SkuDTO skuDtoDb = skuServiceImp.dbToDTO(skuDdb);
        skuDTO.setId(skuDtoDb.getId());

        log.info(">>  user : {}", skuDTO.toString());
        log.info(">>  user : {}", skuDtoDb.toString());

        Assertions.assertEquals(skuDdb.getSkuString(), skuDTO.getSkuString());
        Assertions.assertEquals(skuDTO, skuDtoDb);


    }

    //teste para pegar dados do local e cidade pelo unix
    @Test
    public void findByUnix() {


        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("H");
        jogos.add("L");
        jogos.add("V");
        skuDTO.setDistribuidora("CH");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n 299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond()+10);
        skuDTO.setUsername("thiagonixi");
        long unixsearch = skuDTO.getUnixTime();
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        System.out.println(skuDTO.getSkuString());
        skuServiceImp.salvarNovoSku(skuDTO);

        LocalCidade localCidade = skuServiceImp.getLocalCidadeByUnix((unixsearch));
        Sku sku = skuServiceImp.findBySku(util.conversorSkuDtoParaSku(skuDTO));
        SkuDTO dbDTO = skuServiceImp.dbToDTO(sku);
        System.out.println(localCidade.toString());

        Assertions.assertEquals(skuDTO.getLocal(), localCidade.getLocal());
        Assertions.assertEquals(skuDTO.getCidade(), localCidade.getCidade());

    }

    @Test
    public void testFindById() {

        Sku sku = skuServiceImp.findById(3);
        SkuDTO dbDTO = skuServiceImp.dbToDTO(sku);
        System.out.println(dbDTO);

    }


    @Test
    public void testListaLocais() {
        SkuDTO skuDTO = new SkuDTO();
        List<String> jogos = new ArrayList<>();
        jogos.add("H");
        jogos.add("L");
        jogos.add("V");
        skuDTO.setDistribuidora("US");
        skuDTO.setLingua("EN");
        skuDTO.setContrato("MJ");
        skuDTO.setUsername("thiagonixi");
        skuDTO.setPorcentagem("30%");
        skuDTO.setQuantidadePlacas(9);
        skuDTO.setNumeroJogos(3);
        skuDTO.setJogos(jogos);
        skuDTO.setLocal("Rua natal n299");
        skuDTO.setCidade("Natal");
        skuDTO.setUnixTime(Instant.now().getEpochSecond()+213);
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        skuServiceImp.salvarNovoSku(skuDTO);

        SkuDTO skuDTO2 = new SkuDTO();
        List<String> jogos2 = new ArrayList<>();
        jogos.add("H");
        jogos.add("D");
        skuDTO2.setDistribuidora("CH");
        skuDTO2.setLingua("ES");
        skuDTO2.setContrato("MJ");
        skuDTO2.setPorcentagem("30%");
        skuDTO2.setQuantidadePlacas(9);
        skuDTO2.setNumeroJogos(2);
        skuDTO2.setUsername("thiagonixi");
        skuDTO2.setJogos(jogos2);
        skuDTO2.setLocal("Rua china n299");
        skuDTO2.setCidade("brasil");
        skuDTO2.setUnixTime(Instant.now().getEpochSecond() + 2);
        skuDTO2.setSkuString(util.conversorSkuDtoParaSku(skuDTO2));
        skuServiceImp.salvarNovoSku(skuDTO2);

        List<LocalCidade> lista = skuServiceImp.findListLocalCidade();

        Assertions.assertEquals(skuDTO.getCidade(), lista.get(1).getCidade());
        Assertions.assertEquals(skuDTO.getLocal(), lista.get(1).getLocal());
        Assertions.assertEquals(skuDTO2.getCidade(), lista.get(2).getCidade());
        Assertions.assertEquals(skuDTO2.getLocal(), lista.get(2).getLocal());


    }


    @Test
    public void testListaSku() {
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
        skuDTO.setUsername("thiagonixi");
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
        skuDTO2.setUsername("thiagonixi");
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
        skuDTO3.setUsername("thiagonixi");
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

    @Test
    public void testDeletar() {
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
        skuDTO.setUsername("thiagonixi");
        skuDTO.setUnixTime(Instant.now().getEpochSecond()+2);
        skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
        skuServiceImp.salvarNovoSku(skuDTO);

        SkuDTO skuDTO2 = new SkuDTO();
        List<String> jogos2 = new ArrayList<>();
        jogos.add("H");
        jogos.add("D");
        skuDTO2.setDistribuidora("CH");
        skuDTO2.setLingua("ES");
        skuDTO2.setContrato("MJ");
        skuDTO2.setPorcentagem("30%");
        skuDTO2.setQuantidadePlacas(9);
        skuDTO2.setNumeroJogos(2);
        skuDTO2.setUsername("thiagonixi");
        skuDTO2.setJogos(jogos2);
        skuDTO2.setLocal("Rua china n299");
        skuDTO2.setCidade("brasil");
        skuDTO2.setUnixTime(Instant.now().getEpochSecond() + 2);
        skuDTO2.setSkuString(util.conversorSkuDtoParaSku(skuDTO2));
        skuServiceImp.salvarNovoSku(skuDTO2);
        Assertions.assertTrue(skuServiceImp.skuExists(skuDTO2.getSkuString()));

        LocalCidade localCidade = skuServiceImp.getLocalCidadeByUnix(skuDTO2.getUnixTime());
        skuServiceImp.deleteLocalCidade(localCidade.getId());

        Assertions.assertFalse(skuServiceImp.skuExists(skuDTO2.getSkuString()));


    }

}