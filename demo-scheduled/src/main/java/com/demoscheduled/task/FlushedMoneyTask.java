package com.demoscheduled.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demoscheduled.entity.TaskInfo;
import com.demoscheduled.mapper.TaskInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class FlushedMoneyTask implements SchedulingConfigurer {
    @Resource
    private ThreadPoolTaskScheduler taskScheduler;
    @Resource
    private TaskInfoMapper taskInfoMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler);
        Runnable task = () -> {
            log.info("开始读取账单文件");
            log.info("已读取账单文件，进行计算");
            log.info("用户预付金额已刷新");
        };
        Trigger trigger = (TriggerContext triggerContext) -> {
            TaskInfo taskInfo = taskInfoMapper.selectOne(new QueryWrapper<TaskInfo>().lambda().eq(TaskInfo::getJobName, "flushedMoneyTask"));
            return new CronTrigger(taskInfo.getCron()).nextExecutionTime(triggerContext);
        };
        taskRegistrar.addTriggerTask(task, trigger);
    }
}
