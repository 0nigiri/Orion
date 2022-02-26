package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.Sku;
import com.desafio.orion.models.SkuDTO;
import com.desafio.orion.services.SkuServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {
    public Utils util = new Utils();
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private SkuServiceImp skuService;

    @GetMapping("/listaEspera")
    public String listaLocal(Model model) {
        model.addAttribute("listaSKU", skuService.findAlltoDTO());
        return "user/listaLocal";
    }

    @GetMapping("/listaAprovado")
    public String listaLocalAprovado(Model model) {
        model.addAttribute("listaSKU", skuService.findAlltoDTO());
        return "user/listaLocalAprovado";
    }


    @GetMapping("/add")
    public String addLocal(Model model) {
        List<String> listaJogo = util.listaJogos();
        model.addAttribute("skuDTO", new SkuDTO());
        model.addAttribute("listaJogo", listaJogo);
        return "user/registrarLocal";
    }

    @PostMapping("/add")
    public String addLocal(@Valid SkuDTO skuDTO, BindingResult result, Model model) {
        log.info(">>  skuDTO : {}", skuDTO.toString());
        //Validador dados
        skuDTO.getJogos().removeIf(String::isEmpty);
        if (skuDTO.getNumeroJogos() != skuDTO.getJogos().size()) {
            result.addError(new FieldError("userDTO", "numeroJogos"
                    , "Numero de jogos esta invalido!"));
        }
        if (skuDTO.getJogos().isEmpty()) {
            result.addError(new FieldError("userDTO", "getJogos"
                    , "Numero de jogos esta invalido!"));
        }

        if (skuDTO.getContrato().equals("UJ") && skuDTO.getJogos().size() > 1) {
            result.addError(new FieldError("userDTO", "contrato"
                    , "Numero de jogo esperado esta acima do contrato"));
        }
        if (skuDTO.getContrato().equals("MJ") && skuDTO.getJogos().size() == 1) {
            result.addError(new FieldError("userDTO", "contrato"
                    , "Numero de jogo esperado esta abaixo do contrato"));
        }

        if (result.hasErrors()) {
            List<String> listaJogo = util.listaJogos();
            skuDTO.getJogos().clear();
            model.addAttribute("listaJogo", listaJogo);
            return "user/registrarLocal";
        }
        skuDTO.setUnixTime(Instant.now().getEpochSecond());
        skuService.salvarNovoSku(skuDTO);
        return "redirect:/user/listaEspera";
    }


    @GetMapping("/update/{id}")
    public String updateLocal(@PathVariable(name = "id") long id, Model model) {
        List<String> listaJogo = util.listaJogos();
        model.addAttribute("listaJogo", listaJogo);

        if (model.containsAttribute("skuDTO")) {
            return "user/editarLocal";
        }

        Sku sku = skuService.findById(id);
        SkuDTO dbDTO = skuService.dbToDTO(sku);
        model.addAttribute("skuDTO", dbDTO);
        return "user/editarLocal";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") long id, @Valid SkuDTO skuDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        log.info(">>  skuDTO : {}", skuDTO.toString());
        skuDTO.getNewJogos().removeIf(String::isEmpty);
        skuDTO.getJogos().removeIf(String::isEmpty);
        if (skuDTO.getNumeroJogos() != skuDTO.getNewJogos().size()) {
            result.addError(new FieldError("userDTO", "numeroJogos"
                    , "Numero de jogos esta invalido!"));
        }
        if (skuDTO.getNewJogos().isEmpty()) {
            result.addError(new FieldError("userDTO", "getNewJogos"
                    , "Numero de jogos esta invalido!"));

        }
        if (skuDTO.getContrato().equals("UJ") && skuDTO.getNewJogos().size() > 1) {
            result.addError(new FieldError("userDTO", "contrato"
                    , "Numero de jogo esperado esta acima do contrato"));
        }
        if (skuDTO.getContrato().equals("MJ") && skuDTO.getNewJogos().size() == 1) {
            result.addError(new FieldError("userDTO", "contrato"
                    , "Numero de jogo esperado esta abaixo do contrato"));
        }

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("skuDTO", skuDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.skuDTO", result);
            skuDTO.getNewJogos().clear();
            return "redirect:/user/update/" + id;
        }
        skuDTO.getJogos().clear();
        skuDTO.getJogos().addAll(skuDTO.getNewJogos());
        skuService.salvarNovoSku(skuDTO);
        return "redirect:/user/listaEspera";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/aprovar/{id}")
    public String aprovarLocal(@PathVariable(name = "id") long id, Model model) {
        List<String> listaJogo = util.listaJogos();
        model.addAttribute("listaJogo", listaJogo);

        if (model.containsAttribute("skuDTO")) {
            return "admin/aprovarLocal";
        }

        Sku sku = skuService.findById(id);
        SkuDTO dbDTO = skuService.dbToDTO(sku);
        model.addAttribute("skuDTO", dbDTO);
        return "admin/aprovarLocal";
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/aprovar/{id}")
    public String aprove(@PathVariable(name = "id") long id, @Valid SkuDTO skuDTO, Model model) {
        log.info(">>  skuDTO : {}", skuDTO.toString());

        skuDTO.setApproved(true);
        skuService.salvarNovoSku(skuDTO);
        return "redirect:/user/listaEspera";
    }


    @GetMapping("/delete/{id}")
    public String deletar(@PathVariable(name = "id") long id, SkuDTO skuDTO, Model model) {
        log.info(">>  skuDTO : {}", skuDTO.toString());
        skuService.deleteLocalCidade(id);
        return "redirect:/user/listaEspera";
    }


}
