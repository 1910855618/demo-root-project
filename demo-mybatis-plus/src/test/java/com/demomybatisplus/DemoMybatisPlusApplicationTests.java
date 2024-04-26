package com.demomybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.demomybatisplus.entity.User;
import com.demomybatisplus.mapper.UserMapper;
import com.demomybatisplus.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@Log4j2
@SpringBootTest
class DemoMybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    // 借阅 https://www.quanxiaoha.com/mybatis-plus/mybaitsplus-select-data.html

    @Test
    void test() {
        List<User> users = userMapper.selectList(null);
        log.info("users: {}", users);
        users.forEach(item -> log.info("name: {}", item::getName));
    }

    @Test
    void mybatisPlusInsetTest() {
        // BaseMapper.insert(T) 插入
//        User user = User.builder().name("Annie").age(16).gender(0).build();
//        userMapper.insert(user);
//        log.info("已新增ID为: {}", user.getId());

        // IService.save(T) 插入
//        User user = User.builder().name("Annie").age(16).gender(0).build();
//        boolean isOk = userService.save(user);
//        log.info("isOk: {}, userId: {}", isOk, user.getId());

        // IService.saveBatch(Collection) | IService.saveBatch(Collection, int) 伪批量插入
//        List<User> users = new ArrayList<>();
//        for(int i=0;i<5;i++) {
//            users.add(User.builder().name("Annie").age(16).gender(0).build());
//        }
//        boolean isOk = userService.saveBatch(users);
//        log.info("isOk: {}", isOk);

        // IService.saveOrUpdate(T) 插入或更新
//        User user = User.builder()
//                // 设置了主键字段，当查询库中存在该主键时不执行 inset 而是 update
//                .id(2L)
//                .name("Annie").age(16).gender(0).build();
//        boolean isOk = userService.saveOrUpdate(user);
//        log.info("isOk: {}, userId: {}", isOk, user.getId());

        // IService.saveOrUpdateBatch(Collection) | IService.saveOrUpdateBatch(Collection, int) 伪批量插入或更新
        List<User> users = new ArrayList<>();
        for(int i=0;i<5;i++) {
            users.add(User.builder()
                    // 设置了主键字段，当查询库中存在该主键时不执行 inset 而是 update
                    .id(2L)
                    .name("Annie").age(16).gender(0).build());
        }
        boolean isOk = userService.saveOrUpdateBatch(users, 2);
        log.info("isOk: {}", isOk);
    }

    @Test
    void mybatisPlusDeleteTest() {
        // BaseMapper.deleteById(Serializable) | BaseMapper.deleteById(T) 根据主键 ID 删除
//        int count = userMapper.deleteById(2L);
//        log.info("count: {}", count);

        // BaseMapper.deleteBatchIds(Collection) 根据主键 ID 批量删除
//        int count = userMapper.deleteBatchIds(Arrays.asList(2L, 3L));
//        log.info("count: {}", count);

        // BaseMapper.delete(Wrapper) 通过 Wrapper 条件构造器删除
//        int count = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getId, 3L));
//        log.info("count: {}", count);

        // BaseMapper.deleteByMap(Map) 通过 Map 设置条件来删除
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("id", 4L);
//        int count = userMapper.deleteByMap(columnMap);
//        log.info("count: {}", count);

        // IService.remove(Wrapper) 通过 Wrapper 条件构造器删除
//        boolean isOk = userService.remove(new QueryWrapper<User>().lambda().eq(User::getId, 2L));
//        log.info("isOk: {}", isOk);

        // IService.removeById(Serializable) | IService.removeById(T) 根据 ID 删除
//        boolean isOk = userService.removeById(2L);
//        log.info("isOk: {}", isOk);

        // IService.removeByIds(Collection) 根据主键 ID 批量删除
//        boolean isOk = userService.removeByIds(Arrays.asList(1L, 2L));
//        log.info("isOk: {}", isOk);

        // IService.removeByMap(Map) 通过 Map 设置条件来删除
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", 4L);
        boolean isOk = userService.removeByMap(columnMap);
        log.info("isOk: {}", isOk);
    }

    @Test
    void mybatisPlusUpdateTest() {
        // BaseMapper.updateById(T) 根据 Id 更新
//        int count = userMapper.updateById(User.builder().id(5L).name("Jack").gender(1).build());
//        log.info("count: {}", count);

        // BaseMapper.update(T, Wrapper) 通过 Wrapper 条件构造器修改，修改内容为 T
//        int count = userMapper.update(User.builder().name("Annie").gender(0).build(), new UpdateWrapper<User>().lambda().eq(User::getId, 5L));
//        log.info("count: {}", count);

        // IService.updateById(T) 根据 Id 更新
//        boolean isOk = userService.updateById(User.builder().id(5L).name("Jack").gender(1).build());
//        log.info("isOk: {}", isOk);

        // IService.update(Wrapper) 通过 Wrapper 条件更新，更新内容为 Wrapper 中 set 的内容
//        boolean isOk = userService.update(new UpdateWrapper<User>().lambda().eq(User::getId, 5L).set(User::getName, "Jack").set(User::getGender, 1));
//        log.info("isOk: {}", isOk);

        // IService.update(T, Wrapper) 通过 Wrapper 条件构造器修改，修改内容为 T
//        boolean isOk = userService.update(User.builder().name("Jack").gender(1).build(), new UpdateWrapper<User>().lambda().eq(User::getId, 1L));
//        log.info("isOk: {}", isOk);

        // IService.updateBatchById(Collection) | IService.updateBatchById(Collection, int) 根据 Id 批量更新
        boolean isOk = userService.updateBatchById(Arrays.asList(User.builder().id(5L).name("Annie").gender(0).build(), User.builder().id(6L).name("Jack").gender(1).build()));
        log.info("isOk: {}", isOk);

    }

}
