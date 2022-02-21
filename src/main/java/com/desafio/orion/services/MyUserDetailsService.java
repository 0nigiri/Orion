package com.desafio.orion.services;

import com.desafio.orion.config.MyUserDetails;
import com.desafio.orion.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImp userServiceImp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = Optional.ofNullable(userServiceImp.getUserByUsername(username));
        user.orElseThrow(()->new UsernameNotFoundException(username + "Not Found!"));
        return user.map(MyUserDetails::new).get();
    }

}

