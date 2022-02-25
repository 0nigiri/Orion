package com.desafio.orion.repository;

import com.desafio.orion.common.Utils;
import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.services.UserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertTrue(userServiceImp.userExists(user.getUsername()));


    }

    @Test
    public void testFindByEmail() {
        String email = "admin@gmail.com";
        Assertions.assertTrue(userServiceImp.emailExists(email));

    }

    @Test
    public void testIfDBIsNotEmpty() {
        List<User> list = new ArrayList<>();
        list = userServiceImp.listAll();
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    public void testUpdateData() {
        User getUser = userServiceImp.getUserByUsername("admin");

        Assertions.assertTrue(getUser.getUsername().equals("admin"));
        UserDTO user = userServiceImp.updateToDTO(getUser);
        Assertions.assertTrue(user.getUsername().equals("admin"));

        user.setFirstName("thiago");
        user.setLastName("onishi");

        userServiceImp.salvar(user);

        User checkUser = userServiceImp.getUserByUsername("admin");
        Assertions.assertTrue(checkUser.getFirstName().equals("thiago"));
        Assertions.assertTrue(checkUser.getLastName().equals("onishi"));
        Assertions.assertTrue(encoder.matches("admin", checkUser.getPassword()));


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

        Assertions.assertTrue(encoder.matches("123456", userTest.getPassword()));

        userServiceImp.delete(userTest.getId());

        Assertions.assertFalse(userServiceImp.emailExists("thi1agox@gmail.com"));


    }


}