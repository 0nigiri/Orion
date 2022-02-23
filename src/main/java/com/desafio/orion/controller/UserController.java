package com.desafio.orion.controller;

import com.desafio.orion.models.User;
import com.desafio.orion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String index(){
        return "";
    }

    @GetMapping("/listaSku")
    public String listaSku(){
        return "user/listaSku";
    }

    @GetMapping("/listaEspera")
    public String listaSkuAtivados(){
        return "user/listaSkuAtivados";
    }




}
