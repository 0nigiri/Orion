package com.desafio.orion.repository;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.UserServiceImp;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserServiceImp userServiceImp;

    Utils util = new Utils();

    PasswordEncoder encoder = new BCryptPasswordEncoder();



    @Test
    public void testEnviarUsuario() {
        UserDTO user = new UserDTO();
        user.setEmail("thi1ago@gmail.com");
        user.setUsername("thiago");
        user.setPassword("123456");
        user.setFirstName("thiago");
        user.setLastName("onishi");
        user.setRut("123123123");
        user.setActive(true);

        userServiceImp.salvar(user);
        assertTrue(userServiceImp.userExists(user.getUsername()));


    }

    @Test
    public void testFindByEmail() {
        String email = "thi1ago@gmail.com";
        assertTrue(userServiceImp.emailExists(email));

    }

    @Test
    public void testIfDBIsNotEmpty() {
        List<User> list = new ArrayList<>();
        list = userServiceImp.listAll();
        assertFalse(list.isEmpty());
    }

    @Test
    public void testEncoderPasswordAndDelete() {
        UserDTO user = new UserDTO();
        user.setEmail("thi1agox@gmail.com");
        user.setUsername("thiagox");
        user.setPassword("123456");
        user.setFirstName("thiago");
        user.setLastName("onishi");
        user.setRut("123123123");
        user.setActive(true);
        user.setPassword(util.passwordEncoder(user.getPassword()));
        userServiceImp.salvar(user);


        User userTest = new User();
        userTest = userServiceImp.getUserByUsername(user.getUsername());

        assertTrue(encoder.matches("123456",userTest.getPassword()));

        userServiceImp.delete(userTest.getId());

        assertFalse(userServiceImp.emailExists("thi1agox@gmail.com"));


    }


}