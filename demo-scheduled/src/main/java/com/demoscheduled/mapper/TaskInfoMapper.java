package com.demoscheduled.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demoscheduled.entity.TaskInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskInfoMapper extends BaseMapper<TaskInfo> {
}
