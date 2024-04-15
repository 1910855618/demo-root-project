package com.demojdbc.dao;

import com.demojdbc.model.dto.UserDTO;

import java.util.List;

public interface UserDao {
    int save(UserDTO userDTO);

    int change(UserDTO userDTO);

    int deleteById(int id);

    List<UserDTO> queryAll();

    UserDTO getUserById(int id);
}
