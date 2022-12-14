package com.icolak.service;

import com.icolak.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO roleDTO);
    UserDTO findById(String username);
    List<UserDTO> findAll();
    void deleteById(String username);

}
