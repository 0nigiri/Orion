package com.desafio.orion.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.desafio.orion.validator.RutNumberValidator.dv;

class RutNumberValidatorTest {
    @Test
    public void testarValidadorRUT() {

        String rut = "1844579-4";
        rut = rut.replace(".", "");
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);

        if (matcher.matches() == false) {
            assertTrue(matcher.matches() == false);
        } else {
            String[] stringRut = rut.split("-");
            assertTrue(stringRut[1].toLowerCase().equals(dv(stringRut[0])));
        }

    }


}