package com.desafio.orion.controller;

import com.desafio.orion.common.Utils;
import com.desafio.orion.services.UserServiceImp;
import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class AdminControllerTest {

    @Autowired
    private UserServiceImp userService;

    @Test
    public void testPasswordChange(){
        String password = "admin";
        String newPassword = "novaSenha";
        String username = "admin";
        assertTrue(userService.passwordChange(username,password));
        assertFalse(userService.passwordChange(username,newPassword));


    }


}