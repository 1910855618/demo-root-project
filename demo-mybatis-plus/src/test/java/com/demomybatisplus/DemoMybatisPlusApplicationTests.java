package com.demomybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demomybatisplus.entity.User;
import com.demomybatisplus.mapper.UserMapper;
import com.demomybatisplus.model.vo.OrderVO;
import com.demomybatisplus.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Function;

@SuppressWarnings("all")
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

    /**
     *
     * 插入操作
     * */
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
//                    .id(2L)
                    .name("Annie").age(16).gender(0).build());
        }
        boolean isOk = userService.saveOrUpdateBatch(users, 2);
        log.info("isOk: {}", isOk);
    }

    /**
     *
     * 删除操作
     * */
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

    /**
     *
     * 更新操作
     * */
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

    /**
     *
     * 查询操作
     * */
    @Test
    void mybatisPlusSelectTest() {
        // BaseMapper.selectById(Serializable) 根据 Id 查询
//        User user = userMapper.selectById(5);
//        log.info("user: {}", user);

        // BaseMapper.selectOne(Wrapper) 通过 Wrapper 组装查询条件，查询一条记录
//        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 5));
//        log.info("user: {}", user);

        // BaseMapper.selectBatchIds(Collection) 根据多个 ID 批量查询
//        List<User> users = userMapper.selectBatchIds(Arrays.asList(5L, 6L, 7L));
//        log.info("users: {}", users);

        // BaseMapper.selectList(Wrapper) 通过 Wrapper 组装查询条件
//        List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("users: {}", users);

        // BaseMapper.selectByMap(Map) 根据 Map 来设置条件
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name", "Annie");
//        List<User> users = userMapper.selectByMap(columnMap);
//        log.info("users: {}", users);

        // BaseMapper.selectMaps(Wrapper) 通过 Wrapper 组装查询条件，以 map 形式返回
//        List<Map<String, Object>> users = userMapper.selectMaps(new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("users: {}", users);

        // BaseMapper.selectObjs(Wrapper) 通过 Wrapper 组装查询条件，只返回第一个字段的值
//        List<Object> ids = userMapper.selectObjs(new QueryWrapper<User>().lambda().eq(User::getName, "Annie")/*.select(User::getName)*/);
//        log.info("users: {}", ids);

        // IService.getById(Serializable) 根据 ID 查询
//        User user = userService.getById(5);
//        log.info("user: {}", user);

        // IService.getOne(Wrapper) 通过 Wrapper 组装查询条件，查询一条记录
//        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getId, 6L));
//        log.info("user: {}", user);

        // IService.getMap(Wrapper) 通过 Wrapper 组装查询条件，查询一条记录，以 map 形式返回
//        Map<String, Object> user = userService.getMap(new QueryWrapper<User>().lambda().eq(User::getId, 6L));
//        log.info("user: {}", user);

        // IService.getObj(Wrapper, Function) 根据 Wrapper 查询一条记录，通过 Function 格式化查询出的数据
//        Long id = userService.getObj(new QueryWrapper<User>().lambda().eq(User::getId, 6L), o -> Long.parseLong(o.toString()));
//        log.info("id: {}", id);

        // IService.list() 全查 | IService.list(Wrapper) 根据 Wrapper 查询
//        List<User> users = userService.list(new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("users: {}", users);

        // IService.listByIds(Collection) 根据 ID 批量查询
//        List<User> users = userService.listByIds(Arrays.asList(1, 2, 3));
//        log.info("users: {}", users);

        // IService.listByMap(Map) 根据 Map 查询
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name", "Annie");
//        List<User> users = userService.listByMap(columnMap);
//        log.info("users: {}", users);

        // IService.listMaps() 全查，以 map 形式返回 | IService.listMaps(Wrapper) 根据 Wrapper 查询，以 map 形式返回
//        List<Map<String, Object>> users = userService.listMaps(new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("users: {}", users);

        // IService.listObjs() 全查（只会返回首列） | IService.listObjs(Function) 全查，通过 Function 格式化查询出的数据 |
        // IService.listObjs(Wrapper) 更具 Wrapper 查询 | IService.listObjs(Wrapper, Function) 根据 Wrapper 查询，通过 Function 格式化查询出的数据
        List<Long> ids = userService.listObjs(new QueryWrapper<User>().lambda().eq(User::getName, "Annie"), o -> Long.parseLong(o.toString()));
        log.info("ids: {}", ids);
    }

    /**
     *
     * 分页查询操作
     * */
    @Test
    void mybatisPlusSelectPageTest() {
        // BaseMapper.selectPage(IPage, Wrapper) IPage 用于设置需要查询的页数，以及每页展示数据量，Wrapper 用于组装查询条件
        // 查询第 2 页数据，每页 10 条
//        Page<User> page = new Page<>(2, 10);
//        page = userMapper.selectPage(page, new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("total: {}, pages: {}, current: {}, users: {}", page.getTotal(), page.getPages(), page.getCurrent(), page.getRecords());

        // BaseMapper.selectMapsPage(IPage, Wrapper) 分页查询，用 map 接收查询出的数据
//        Page<Map<String, Object>> page = new Page<>(2, 10);
//        page = userMapper.selectMapsPage(page, new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("total: {}, pages: {}, current: {}, users: {}", page.getTotal(), page.getPages(), page.getCurrent(), page.getRecords());

        // IService.page(IPage) 无条件分页查询 | IService.page(IPage, Wrapper) 按 Wrapper 分页查询
//        Page<User> page = userService.page(new Page<User>(2, 10), new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
//        log.info("total: {}, pages: {}, current: {}, users: {}", page.getTotal(), page.getPages(), page.getCurrent(), page.getRecords());

        // IService.pageMaps(IPage) 无条件分页查询，用 map 接收查询出的数据 | IService.pageMaps(IPage, Wrapper) 按 Wrapper 分页查询，用 map 接收
        Page<Map<String, Object>> page = userService.pageMaps(new Page<Map<String, Object>>(2, 10), new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
        log.info("total: {}, pages: {}, current: {}, users: {}", page.getTotal(), page.getPages(), page.getCurrent(), page.getRecords());
    }

    /**
     *
     * 联表查询操作
     * */
    @Test
    void mybatisPlusSelectRelevancyTest() {
        // 关联查询
//        List<OrderVO> OrderVOS = userMapper.selectOrder(new QueryWrapper<OrderVO>().eq("name", "Annie"));
//        log.info("OrderVOS: {}", OrderVOS);

        // 分页关联查询
        Page<OrderVO> page = new Page<>(2, 2);
        // 需要手动关闭 SQL 优化，不然查询总数的时候只会查询主表
        page.setOptimizeCountSql(false);
        IPage<OrderVO> iPage = userMapper.selectOrderPage(page, new QueryWrapper<OrderVO>().eq("name", "Annie"));
        log.info("total: {}, pages: {}, current: {}, users: {}", iPage.getTotal(), iPage.getPages(), iPage.getCurrent(), iPage.getRecords());
    }

    /**
     *
     * 阻止全表更新或删除操作
     * */
    @Test
    void mybatisPlusAllTableTest() {
        // 无条件更新（全表更新）会抛出异常 MyBatisSystemException
//        userService.saveOrUpdate(User.builder().gender(0).build(), null);
        // 无条件删除（全表删除）会抛出异常 MyBatisSystemException
//        userService.remove(null);
        // 有条件的更新或删除不会被阻止
        userService.update(User.builder().gender(0).build(), new QueryWrapper<User>().lambda().eq(User::getName, "Annie"));
    }

    /**
     *
     * yml 配置加密
     * */
    @Test
    void mybatisPlusEncryptTest() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        // 随机密钥加密
        String result = AES.encrypt("123456", randomKey);
        // 解密
        String decrypt = AES.decrypt(result , randomKey);
        // 在 yml 配置文件中要加密值的前面加上“mpw:加密结果”，之后启动 jar 包时追加“--mpw.key=随机密钥”启动参数即可
        log.info("随机密钥: {}, 加密结果: {}, 解密结果: {}", randomKey, result, decrypt);
    }
}
