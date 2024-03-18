package com.demohikaricp.mapper.secondary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface SecondaryUserMapper {
    @Select("select * from sys_user limit 1")
    Map<String, Object> queryAll();
}
