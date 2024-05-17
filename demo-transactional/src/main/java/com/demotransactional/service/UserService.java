package com.demotransactional.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demotransactional.entity.User;
import org.springframework.transaction.annotation.Transactional;

// 不推荐在接口上使用 @Transactional 注解
//@Transactional
public interface UserService extends IService<User> {
    boolean testSaveProgrammatic(User user);

    boolean testSaveProgrammaticManager(User user);

    boolean testSave(User user);

    boolean testSaveWrap(User user);
}
