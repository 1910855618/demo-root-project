package com.demojdbc.dao.impl;

import com.demojdbc.dao.UserDao;
import com.demojdbc.mapper.UserMapper;
import com.demojdbc.model.dto.UserDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(UserDTO userDTO) {
        String sql = "insert into user(name, age, email, manager_id, create_time) values(:name, :age, :email, :managerId, now());";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(Objects.requireNonNull(jdbcTemplate.getDataSource()));
        return npjt.update(sql, new BeanPropertySqlParameterSource(userDTO));
    }

    @Override
    public int change(UserDTO userDTO) {
        String sql = "update user set name=?, age=?, email=?, manager_id=? where id=?";
        Object[] args = {userDTO.getName(), userDTO.getAge(), userDTO.getEmail(), userDTO.getManagerId(), userDTO.getId()};
        int[] argsTypes = {Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        return jdbcTemplate.update(sql, args, argsTypes);
    }

    @Override
    public int deleteById(int id) {
        String sql = "delete from user where id=?";
        Object[] args = {id};
        int[] argsTypes = {Types.INTEGER};
        return jdbcTemplate.update(sql, args, argsTypes);
    }

    @Override
    public List<UserDTO> queryAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public UserDTO getUserById(int id) {
        String sql = "select * from user where id=?";
        Object[] args = {id};
        int[] argsTypes = {Types.INTEGER};
        List<UserDTO> list = jdbcTemplate.query(sql, args, argsTypes, new UserMapper());
        UserDTO userDTO = new UserDTO();
        if(!list.isEmpty()) {
            userDTO = list.get(0);
        }
        return userDTO;
    }
}
