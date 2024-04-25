package com.demoredis.dao;

import com.demoredis.model.dto.UserDTO;

public interface UserDao {
    int save(UserDTO userDTO);

    UserDTO change(UserDTO userDTO);

    int deleteById(int id);

    UserDTO getUserById(int id);
}
