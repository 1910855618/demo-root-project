package com.demotransactional.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demotransactional.entity.User;
import com.demotransactional.mapper.UserMapper;
import com.demotransactional.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

// 在类上使用 @Transactional 注解表明所有 public 方法都会被事务管理
//@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private PlatformTransactionManager transactionManager;

    // 编程式事务管理, 使用 TransactionTemplate
    @Override
    public boolean testSaveProgrammatic(User user) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    userMapper.insert(user);
                    if(true) {
                        throw new RuntimeException("开始回滚...");
                    }
                } catch (Exception e) {
                    // 执行回滚
                    status.setRollbackOnly();
                    throw e;
                }
            }
        });
        return false;
    }

    // 编程式事务管理, 使用 PlatformTransactionManager
    @Override
    public boolean testSaveProgrammaticManager(User user) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            userMapper.insert(user);
            if(true) {
                throw new RuntimeException("开始回滚...");
            }
            // 提交事务，事务提交需要在最后执行，否则在异常发生前事务已经提交回滚也就不会生效了
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return false;
    }

    // 推荐将注解使用于方法上，@Transactional 注解只能应用到 public 方法上，否则不生效
    @Transactional(
            // 事务的传播行为，默认值为 REQUIRED
            propagation = Propagation.REQUIRED,
            // 事务的隔离级别，默认值采用 DEFAULT
            isolation = Isolation.DEFAULT,
            // 事务的超时时间，默认值为-1（不会超时）。如果超过该时间限制但事务还没有完成，则自动回滚事务
            timeout = -1,
            // 指定事务是否为只读事务，默认值为 false
            readOnly = false,
            // 用于指定能够触发事务回滚的异常类型，并且可以指定多个异常类型
            rollbackFor = {
                    Exception.class,
                    RuntimeException.class
            }
    )
    @Override
    public boolean testSave(User user) {
        userMapper.insert(user);
        if(true) {
            throw new RuntimeException("开始回滚...");
        }
        return false;
    }

    // 这个方法不会触发 testSave 的事务，只有当 @Transactional 注解的方法在类以外被调用的时候，Spring 事务管理才生效
    // 需要尽量避免同一类的方法互相调用
    @Override
    public boolean testSaveWrap(User user) {
        return this.testSave(user);
    }
}
