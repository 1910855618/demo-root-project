package com.demomybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 * 自动填充字段
 * */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     *
     * insert 时填充字段
     * */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("执行 insert 开始填充...");
        strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
    }

    /**
     *
     * update 时填充字段
     * */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("执行 update 开始填充...");
        strictUpdateFill(metaObject, "changeTime", LocalDateTime::now, LocalDateTime.class);
    }
}
