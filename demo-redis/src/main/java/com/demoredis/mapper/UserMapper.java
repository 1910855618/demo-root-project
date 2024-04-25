package com.demoredis.mapper;

import com.demoredis.model.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDTO> {
    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserDTO.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .age(rs.getInt("age"))
                .email(rs.getString("email"))
                .managerId(rs.getInt("manager_id"))
                .createTime(rs.getTimestamp("create_time").toLocalDateTime())
                .build();
    }
}
