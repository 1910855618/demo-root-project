package com.demoredis.dao.impl;

import com.demoredis.dao.UserDao;
import com.demoredis.mapper.UserMapper;
import com.demoredis.model.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Objects;

@CacheConfig(cacheNames = "user")
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

    @CachePut(key = "#p0.id")
    @Override
    public UserDTO change(UserDTO userDTO) {
        String sql = "update user set name=?, age=?, email=?, manager_id=? where id=?";
        Object[] args = {userDTO.getName(), userDTO.getAge(), userDTO.getEmail(), userDTO.getManagerId(), userDTO.getId()};
        int[] argsTypes = {Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER};
        jdbcTemplate.update(sql, args, argsTypes);
        return getUserById(userDTO.getId());
    }

    @CacheEvict(key = "#p0", allEntries = true)
    @Override
    public int deleteById(int id) {
        String sql = "delete from user where id=?";
        Object[] args = {id};
        int[] argsTypes = {Types.INTEGER};
        return jdbcTemplate.update(sql, args, argsTypes);
    }

    @Cacheable(key = "#p0")
    @Override
    public UserDTO getUserById(int id) {
        String sql = "select * from user where id=?";
        Object[] args = {id};
        int[] argsTypes = {Types.INTEGER};
        return jdbcTemplate.query(sql, args, argsTypes, new UserMapper()).get(0);
    }
}
