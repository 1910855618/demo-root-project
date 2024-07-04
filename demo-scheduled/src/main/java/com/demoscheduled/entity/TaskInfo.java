package com.demoscheduled.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.demoscheduled.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_task_info")
public class TaskInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String cron;
    @TableField("job_name")
    private String jobName;
    private StatusEnum status;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "change_time", fill = FieldFill.UPDATE)
    private LocalDateTime changeTime;
}
