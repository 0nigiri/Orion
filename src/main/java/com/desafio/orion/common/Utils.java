package com.desafio.orion.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

   public  String passwordEncoder(String password){
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       String encodedPassword = encoder.encode(password);
       return encodedPassword;
   }

}
