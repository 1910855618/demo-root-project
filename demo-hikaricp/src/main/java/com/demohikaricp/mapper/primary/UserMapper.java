package com.demohikaricp.mapper.primary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("select * from sys_user limit 1")
    Map<String, Object> queryAll();
}
