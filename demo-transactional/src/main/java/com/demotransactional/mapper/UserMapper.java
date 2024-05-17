package com.demotransactional.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demotransactional.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
