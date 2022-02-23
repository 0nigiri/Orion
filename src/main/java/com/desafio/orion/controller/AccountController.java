package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AccountController {

    private final UserServiceImp userServiceImp;

    public AccountController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    Utils utils = new Utils();

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);


    @GetMapping("register")
    public String registrar(UserDTO userDTO, Model model) {
        model.addAttribute("userDTO", new UserDTO());

        return "account/register";
    }



    @PostMapping("register")
    public String registerProcess(@Valid  UserDTO userDTO,BindingResult result, Model model) {

        //Validador dados
        if (userServiceImp.userExists(userDTO.getUsername())) {
            result.addError(new FieldError("userDTO", "username"
                    , "Usuario ja esta em uso"));
        }
        if (userServiceImp.emailExists(userDTO.getEmail())) {
            result.addError(new FieldError("userDTO", "email"
                    , "Email ja esta em uso"));
        }
        if (result.hasErrors()) {
            return "account/register";
        }

        //salvar novo usuario
        log.info(">>  user : {}", userDTO.toString());
        userDTO.setActive(true);
        userDTO.setPassword(utils.passwordEncoder(userDTO.getPassword()));
        userServiceImp.salvar(userDTO);
        return "account/registerCheck";
    }

    @GetMapping("login")
    public String homePage() {
        return "account/login";
    }



}
