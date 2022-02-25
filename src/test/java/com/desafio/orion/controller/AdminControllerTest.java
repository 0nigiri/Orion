package com.desafio.orion.controller;

import com.desafio.orion.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class AdminControllerTest {

    @Autowired
    private UserServiceImp userService;


}