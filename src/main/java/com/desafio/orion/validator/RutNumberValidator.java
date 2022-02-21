package com.desafio.orion.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RutNumberValidator implements
        ConstraintValidator<IsValidRut, String> {
    private static final String RUT_PATTERN = "^[0-9]+-[0-9kK]{1}$";
    private Pattern pattern;
    private Matcher matcher;



    public RutNumberValidator() {
        pattern = Pattern.compile(RUT_PATTERN);
    }

    @Override
    public void initialize(IsValidRut isValidRut) {
        isValidRut.message();
    }

    @Override
    public boolean isValid(String rut, ConstraintValidatorContext context) {
        if (rut == null) {

            return false;
        }
        rut = rut.replace(".", "");
        matcher = pattern.matcher(rut);
        if (matcher.matches() == false) {
            return false;
        }

        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(dv(stringRut[0]));
    }

    public static String dv(String rut) {
        Integer M = 0, S = 1, T = Integer.parseInt(rut);
        for (; T != 0; T = (int) Math.floor(T /= 10))
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        return (S > 0) ? String.valueOf(S - 1) : "k";
    }


}
