package com.desafio.orion.common;

import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.SkuServiceImp;
import com.desafio.orion.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Utils util = new Utils();

        if (userservice.listAll().isEmpty()) {
            UserDTO user = new UserDTO("admin", "admin", "admin", "admin", "admin", "123455678", true, "ADMIN");
            user.setPassword(util.passwordEncoder(user.getPassword()));
            userservice.salvar(user);
            UserDTO user2 = new UserDTO("thiago", "onishi", "thiagonixi", "thiago", "admin", "123455678", true, "USER");
            user2.setPassword(util.passwordEncoder(user2.getPassword()));
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
            skuDTO.setUsername("thiagonixi");
            skuDTO.setLocal("Rua natal n299");
            skuDTO.setCidade("Natal");
            skuDTO.setUnixTime(1614276962);
            skuDTO.setSkuString(util.conversorSkuDtoParaSku(skuDTO));
            skuService.salvarNovoSku(skuDTO);

        }


    }
}
