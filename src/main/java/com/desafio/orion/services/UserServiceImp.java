package com.desafio.orion.services;

import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void salvar(UserDTO dto){
        userRepository.save(register(dto));
    }

    public List<User> listAll() {
        return(List<User>) userRepository.findAll();
    }

    public void delete(long id){
        userRepository.delete(userRepository.getById(id));
    }

    public User getUserById(long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new UsernameNotFoundException("Não foi possivel encontrar o usuario: " + id ));
    }

    public User getUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(()-> new UsernameNotFoundException("Não foi possivel encontrar o usuario: " + username ));
    }

    public User getUserByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(()-> new UsernameNotFoundException("Não foi possivel encontrar o email: " + email ));
    }



    public User register(UserDTO userDTO) {
        User user = new User();
        modelMapper.map(userDTO, user);
        return user;

    }


    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
