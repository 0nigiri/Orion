package com.desafio.orion.common;

import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.SkuServiceImp;
import com.desafio.orion.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Component
public class PopulacaoIniciaoBanco implements CommandLineRunner {

    @Autowired
    private UserServiceImp userservice;
    @Autowired
    private SkuServiceImp skuService;

    @Override
    public void run(String... args) throws Exception {
        long id = 2;
        Utils util = new Utils();

        if(userservice.listAll().isEmpty()){
            UserDTO user = new UserDTO("admin", "admin","admin","admin@gmail.com","admin","123455678",true,"ADMIN" );
            UserDTO user2 = new UserDTO("thiago", "onishi","thiagonixi","thiagonixi@gmail.com","admin","123455678",true,"USER" );
            user.setPassword(util.passwordEncoder(user.getPassword()));
            user2.setPassword(util.passwordEncoder(user2.getPassword()));
            userservice.salvar(user);
            userservice.salvar(user2);

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
            skuService.salvarNovoSku(skuDTO);


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
            skuService.salvarNovoSku(skuDTO2);

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
            skuService.salvarNovoSku(skuDTO3);



        }



    }
}
