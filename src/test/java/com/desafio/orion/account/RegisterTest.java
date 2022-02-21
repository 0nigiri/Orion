package com.desafio.orion.account;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterTest {

    @Test
    public void testandoValidadorSenha() {
        final String PASSWORD_VALIDATOR = "^(?=.{12}$)(?=(?:.*?[a-z]){2,})(?=(?:.*?[A-Z]){2,})(?=(?:.*.*\\W){2,})(?!.*(.)\\1{2}).*$";
        Pattern pattern = Pattern.compile(PASSWORD_VALIDATOR);

        String password1 = "EEuueqqwe@@k";
        String password2 = "EEuuuqqwe@@k";
        String password3 = "asdadAA@@dac";
        String password4 = "asdioqwe!!AS";

        Matcher matcher1 = pattern.matcher(password1);
        Matcher matcher2 = pattern.matcher(password2);
        Matcher matcher3 = pattern.matcher(password3);
        Matcher matcher4 = pattern.matcher(password4);

        assertTrue(matcher1.matches());
        assertFalse(matcher2.matches());
        assertTrue(matcher3.matches());
        assertTrue(matcher4.matches());


    }
}
