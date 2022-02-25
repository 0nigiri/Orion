package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    public Utils utils =  new Utils();
    private final UserServiceImp userServiceImp;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);


    public AdminController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/update/{id}")
    public String atualizar(@PathVariable(name =  "id") long id , User user, Model model) {

        model.addAttribute("userDTO", userServiceImp.updateToDTO(userServiceImp.getUserById(id)));

        return "admin/userEdit";
    }
    @PostMapping("/update/{id}")
    public String atualizarConfirm(UserDTO userDTO ,  Model model) {
        log.info(">>  user : {}", userDTO.toString());
        userDTO.setActive(true);
        userServiceImp.salvar(userDTO);
        return  "redirect:/admin/userlist";
    }

    @GetMapping("/delete/{id}")
    public String deletar(@PathVariable(name =  "id") long id, UserDTO userDTO,  Model model) {
        log.info(">>  user : {}", userDTO.toString());
        userServiceImp.deletar(id);
        return  "redirect:/admin/userlist";
    }





    @GetMapping("/userlist")
    public String userlist(Model model) {
        model.addAttribute("userList", userServiceImp.listAll() );

        return "admin/userlist";
    }


}