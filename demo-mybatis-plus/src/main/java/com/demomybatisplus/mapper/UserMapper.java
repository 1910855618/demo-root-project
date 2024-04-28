package com.demomybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.demomybatisplus.entity.User;
import com.demomybatisplus.model.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<OrderVO> selectOrder(@Param(Constants.WRAPPER) Wrapper<OrderVO> queryWrapper);

    IPage<OrderVO> selectOrderPage(IPage<OrderVO> page, @Param(Constants.WRAPPER) Wrapper<OrderVO> queryWrapper);
}
