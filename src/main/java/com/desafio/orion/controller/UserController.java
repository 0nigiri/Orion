package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.Jogos;
import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.SkuServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {
    public Utils util = new Utils();
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private SkuServiceImp skuService;

    @GetMapping("/listaSku")
    public String listaSku(Model model) {
        model.addAttribute("listaSKU", skuService.findAlltoDTO());
        return "user/listaSku";
    }

    @GetMapping("/add")
    public String addSku(Model model) {
        List<String> listaJogo = util.listaJogos();
        model.addAttribute("skuDTO", new SkuDTO());
        model.addAttribute("listaJogo", listaJogo);
        return "user/registrarSku";
    }

    @PostMapping("/add")
    public String addSku(@Valid  SkuDTO skuDTO, BindingResult result, Model model) {
        log.info(">>  skuDTO : {}", skuDTO.toString());
        //Validador dados
        if (skuDTO.getNumeroJogos()!=skuDTO.getJogos().size()) {
            result.addError(new FieldError("userDTO", "numeroJogos"
                    , "Numero de jogos esta invalido!"));
        }
        if(skuDTO.getContrato().equals("UJ") && skuDTO.getJogos().size()>1){
            result.addError(new FieldError("userDTO", "contrato"
                    , "Numero de jogo esperado esta acima do contrato"));
        }
        if(skuDTO.getContrato().equals("MJ") && skuDTO.getJogos().size()==1){
            result.addError(new FieldError("userDTO", "contrato"
                    , "Numero de jogo esperado esta abaixo do contrato"));
        }

        if (result.hasErrors()) {
            List<String> listaJogo = util.listaJogos();
            model.addAttribute("listaJogo", listaJogo);
            return "user/registrarSku";
        }

        skuService.salvarNovoSku(skuDTO);
        return "redirect:/user/listaSku";
    }



    @PostMapping("/update/{id}")
    public String atualizarConfirm(SkuDTO skuDTO,  Model model) {
        log.info(">>  skuDTO : {}", skuDTO.toString());

        return "redirect:/user/listaSku";
    }


    @GetMapping("/update")
    public String updateSku(Model model) {
        model.addAttribute("skuDTO", skuService.findAlltoDTO());
        return "user/editarSku";
    }


}
