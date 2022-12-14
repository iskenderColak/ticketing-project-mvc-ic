package com.icolak.service;

import com.icolak.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO save(RoleDTO roleDTO);
    RoleDTO findById(Long id);
    List<RoleDTO> findAll();
    void deleteById(Long id);

}
