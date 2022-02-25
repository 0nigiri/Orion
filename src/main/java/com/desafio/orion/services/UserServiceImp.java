package com.desafio.orion.services;

import com.desafio.orion.models.User;
import com.desafio.orion.models.UserDTO;
import com.desafio.orion.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void salvar(UserDTO dto) {
        userRepository.save(register(dto));
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void delete(long id) {
        userRepository.delete(userRepository.getById(id));
    }

    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o usuario: " + id));
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o usuario: " + username));
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o email: " + email));
    }

    public void deletar(long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("Não foi possivel encontrar o usuario: " + id));
        userRepository.delete(user.get());
    }


    public User register(UserDTO userDTO) {
        User user = new User();
        modelMapper.map(userDTO, user);
        return user;
    }

    public UserDTO updateToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        modelMapper.map(user, userDTO);
        return userDTO;
    }


    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean idExists(Long id) {
        return userRepository.findById(id).isPresent();
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    public void updateUser(UserDTO userDTO) {
        UserDTO getList = updateToDTO(getUserById(userDTO.getId()));
        userDTO.setLocalCidades(getList.getLocalCidades());
        User user = new User();
        modelMapper.map(userDTO, user);
        userRepository.save(user);
    }
}
