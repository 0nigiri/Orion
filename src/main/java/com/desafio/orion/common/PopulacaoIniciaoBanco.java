package com.desafio.orion.common;

import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class PopulacaoIniciaoBanco implements CommandLineRunner {

    @Autowired
    private UserServiceImp userservice;

    @Override
    public void run(String... args) throws Exception {
        long id = 2;
        Utils utils = new Utils();
        if(userservice.listAll().isEmpty()){
            UserDTO user = new UserDTO("admin", "admin","admin","admin@gmail.com","admin","123455678",true,"ADMIN" );
            UserDTO user2 = new UserDTO("thiago", "onishi","thiagonixi","thiagonixi@gmail.com","admin","123455678",true,"USER" );
            user.setPassword(utils.passwordEncoder(user.getPassword()));
            user2.setPassword(utils.passwordEncoder(user2.getPassword()));
            userservice.salvar(user);
            userservice.salvar(user2);
        }



    }
}
