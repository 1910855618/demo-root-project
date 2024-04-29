package com.demomybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.demomybatisplus.entity.SeckillGoods;
import com.demomybatisplus.entity.User;
import com.demomybatisplus.enums.JobEnum;
import com.demomybatisplus.mapper.SeckillGoodsMapper;
import com.demomybatisplus.mapper.UserMapper;
import com.demomybatisplus.model.vo.OrderVO;
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
    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    // 借阅 https://www.quanxiaoha.com/mybatis-plus/mybatis-plus-integrate.html

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
     * Wrapper 条件构造抽象类
     * -- AbstractWrapper 查询条件封装，用于生成 sql 中的 where 语句。
     *  -- QueryWrapper Entity 条件封装操作类，用于查询。
     *  -- UpdateWrapper Update 条件封装操作类，用于更新。
     *  -- AbstractLambdaWrapper 使用 Lambda 表达式封装 wrapper
     *      -- LambdaQueryWrapper 使用 Lambda 语法封装条件，用于查询。
     *      -- LambdaUpdateWrapper 使用 Lambda 语法封装条件，用于更新。
     * -- AbstractChainWrapper 链式查询条件封装
     *  -- UpdateChainWrapper 链式条件封装操作类，用于更新。
     *  -- LambdaQueryChainWrapper 使用 Lambda 语法封装条件，支持链式调用，用于查询
     *  -- LambdaUpdateChainWrapper 使用 Lambda 语法封装条件，支持链式调用，用于更新
     *  -- QueryChainWrapper 链式条件封装操作类，用于查询。
     * */
    @Test
    void mybatisPlusWrapperTest() {
        // AbstractWrapper 方法
        // 多字段等于查询, 语句: gender=0 and age=18
        Map<String, Object> params = new HashMap<>();
        params.put("gender", "0");
        params.put("age", "18");
        new QueryWrapper<>().allEq(params);
        // 单字段等于, sql: gender=0
        new QueryWrapper<>().eq("gender", "0");
        // 不等于, sql: gender<>1
        new QueryWrapper<>().ne("gender", "1");
        // 大于, sql: age>18
        new QueryWrapper<>().gt("age", 18);
        // 大于等于, sql: age>=18
        new QueryWrapper<>().ge("age", 18);
        // 小于, sql:  age<18
        new QueryWrapper<>().lt("age", 18);
        // 小于等于, sql: age<=18
        new QueryWrapper<>().le("age", 18);
        // 区间查询, sql: dateTime between '2023-1-1' and '2024-1-1'
        new QueryWrapper<>().between("dateTime", "2023-1-1", "2024-1-1");
        // 区间查询反选, sql: dateTime not between '2023-1-1' and '2024-1-1'
        new QueryWrapper<>().notBetween("dateTime", "2023-1-1", "2024-1-1");
        // 全模糊查询, sql: name like '%Annie%'
        new QueryWrapper<>().like("name", "Annie");
        // 全模糊查询反选, sql: name not like '%Annie%'
        new QueryWrapper<>().notLike("name", "Annie");
        // 左模糊查询, sql: name like '%Annie'
        new QueryWrapper<>().likeLeft("name", "Annie");
        // 左模糊查询反选, sql: name not like '%Annie'
        new QueryWrapper<>().notLikeLeft("name", "Annie");
        // 右模糊查询, sql: name like 'Annie%'
        new QueryWrapper<>().likeRight("name", "Annie");
        // 右模糊查询反选, sql: name not like 'Annie%'
        new QueryWrapper<>().notLikeRight("name", "Annie");
        // 为空查询, sql: name is null
        new QueryWrapper<>().isNull("name");
        // 非空查询, sql: name is not null
        new QueryWrapper<>().isNotNull("name");
        // 包含查询, sql: age in(14, 16, 18)
        new QueryWrapper<>().in("age", 14, 16, 18);
        // 包含查询反选, sql: age not in(14, 16, 18)
        new QueryWrapper<>().notIn("age", 14, 16, 18);
        // 子查询, sql: id in(select userId from t_order where goodsPrice > 1000)
        new QueryWrapper<>().inSql("id", "select userId from t_order where goodsPrice > 1000");
        // 子查询反选, sql: id not in(select userId from t_order where goodsPrice > 1000)
        new QueryWrapper<>().notInSql("id", "select userId from t_order where goodsPrice > 1000");
        // 分组, sql: group by name, gender
        new QueryWrapper<>().groupBy("name", "gender");
        // 排序，isAsc true 为升序，false 为降序, sql: order by id asc, age asc
        new QueryWrapper<>().orderBy(true, true, "id", "age");
        // 升序, sql: order by id asc, age asc
        new QueryWrapper<>().orderByAsc("id", "age");
        // 降序, sql: order by id desc, age desc
        new QueryWrapper<>().orderByDesc("id", "age");
        // 过滤分组后数据, sql: having sum(age) > 18
        new QueryWrapper<>().having("sum(age) > {0}", 18);
        // func 方法主要方便在出现 if else 下调用不同方法能不断链, sql: id=1 或者 id<>1
        new QueryWrapper<>().func(i -> {
            if(true) {
                i.eq("id", 1);
            } else {
                i.ne("id", 1);
            }
        });
        // 拼接 or, sql: name='Annie' or name='Emma'
        new QueryWrapper<>().eq("name", "Annie").or().eq("name", "Emma");
        // or 嵌套, sql: or (name='Annie' and name<>'Emma')
        new QueryWrapper<>().or(i -> i.eq("name", "Annie").ne("name", "Emma"));
        // and 嵌套, sql: and (name='Annie' and name<>'Emma')
        new QueryWrapper<>().and(i -> i.eq("name", "Annie").ne("name", "Emma"));
        // nested 正常嵌套不带 and 或者 or, sql: (name='Annie' and name<>'Emma')
        new QueryWrapper<>().nested(i -> i.eq("name", "Annie").ne("name", "Emma"));
        // apply 拼接 sql, sql: id=1 and name='Annie'
        new QueryWrapper<>().apply("id={0} and name={1}", 1, "Annie");
        // last 无视优化规则直接拼接到 sql 的最后, 只会调用一次, 多次调用以最后一次为准, 有 sql 注入的风险, sql: limit 1
        new QueryWrapper<>().last("limit 1");
        // 拼接 exists (在 exists 中语句没有返回值即为假返回 0, 反之为真返回 1), sql: exists (select id from t_user where age=18)
        new QueryWrapper<>().exists("select id from t_user where age=18");
        // 拼接 exists 取反, sql: not exists (select id from t_user where age=18)
        new QueryWrapper<>().notExists("select id from t_user where age=18");
        // QueryWrapper 方法
        // select 设置查询字段, sql: select id, name, age
        new QueryWrapper<>().select("id", "name", "age");
        // checkSqlInjection 检查是否存在 sql 注入, 一般并不需要, 如果前端一定要操作 sql 语句时请加上
        new QueryWrapper<>().checkSqlInjection();
        // UpdateWrapper 方法
        // set 设置更新字段, sql: update set name='Annie'
        new UpdateWrapper<>().set("name", "Annie");
        // setSql 设置更新字段 sql, sql: update set name='Annie'
        new UpdateWrapper<>().setSql("name='Annie'");
        // lambda 获取 LambdaWrapper, QueryWrapper 中使用是获取 LambdaQueryWrapper, UpdateWrapper 中使用是获取 LambdaUpdateWrapper
        LambdaQueryWrapper<?> lambdaQueryWrapper = new QueryWrapper<>().lambda();
        LambdaUpdateWrapper<?> lambdaUpdateWrapper = new UpdateWrapper<>().lambda();
    }

    /**
     *
     * Condition 参数作用
     * */
    @Test
    void mybatisPlusConditionTest() {
        // Wrapper 很多条件判断方法都提供了一个带有 boolean 类型 condition 参数的重载方法, 作用于是否采用这条条件判断
        String name = "Annie";
        Integer ageStart = 10;
        Integer ageEnd = 20;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 如果 name 不为 null 且不为字符串空, 这个 sql where 条件判断将被采用
        wrapper.eq(StringUtils.isNotBlank(name), "name", name)
                // ageStart 不为 null, 这个 sql where 条件判断将被采用
                .ge(ageStart != null, "age", ageStart)
                // ageEnd 不为 null, 这个 sql where 条件判断将被采用
                .le(ageEnd != null, "name", ageEnd);
    }

    /**
     *
     * 代码生成器
     * */
    @Test
    void mybatisPlusCodeGeneratorTest() {
        FastAutoGenerator.create(
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Shanghai&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8",
                "root", "123456")
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("luhang")
                            // 开启 swagger 模式，会自动添加 Swagger 相关注解
                            .enableSwagger()
                            // 指定输出目录
                            .outputDir("C:/Users/PC/Desktop/test");
                }).packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com")
                            // 设置父包模块名
                            .moduleName("demomybatisplus")
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:/Users/PC/Desktop/test/mapper"));
                }).strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude("t_user")
                            // 设置过滤表前缀
                            .addTablePrefix("t_", "c_");
                })
                // 使用 Freemarker 引擎模板，默认的是 Velocity 引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                // 执行
                .execute();
    }

    /**
     *
     * 自动填充
     * */
    @Test
    void mybatisPlusAutofillTest() {
        // createTime 不用赋值应为设置了 fill, 将在适当时机进行填充
//        userService.save(User.builder().name("Lilia").age(18).gender(0).build());
        // changeTime 不用赋值应为设置了 fill, 将在适当时机进行填充
        userService.updateById(User.builder().id(19L).name("Lilia").age(18).gender(0).build());
    }

    /**
     *
     * 通用枚举
     * */
    @Test
    void mybatisPlusGenericEnumTest() {
        // 新增时 mybatis plus 会自动获取到 JobEnum 中的 code 入库
//        userService.save(User.builder().name("Vivien").gender(0).age(16).job(JobEnum.MONITOR).build());
        // 查询时 mybatis plus 会自动获取到 JobEnum 中的 code 进行查询
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getName, "Vivien").eq(User::getJob, JobEnum.MONITOR));
        log.info("user: {}", user);
    }

    /**
     *
     * 乐观锁
     * */
    @Test
    void mybatisPlusOptimisticLocksTest() {
        // 设置当前版本号
        SeckillGoods seckillGoods = SeckillGoods.builder().version(1).build();
        // 在版本号不等于 1 时便不会修改(比如在多线程情况下, 这条被别人更新, 版本号也会被更新不在是 1)
        seckillGoodsMapper.update(seckillGoods,
                new UpdateWrapper<SeckillGoods>().lambda().setSql("count = count - 1").eq(SeckillGoods::getId, 1L));
        // 版本号被更新
        log.info("version: {}", seckillGoods.getVersion());
    }

    /**
     *
     * 逻辑删除
     * */
    @Test
    void mybatisPlusIsDeletedTest() {
        userService.save(User.builder().name("Tirias").age(16).gender(1).build());
        // 修改操作需要检查 isDeleted 字段是否等于 0
        userService.update(User.builder().name("Tirias").age(16).gender(1).build(), new UpdateWrapper<User>().lambda().eq(User::getName, "Tirias"));
        // 查询操作需要检查 isDeleted 字段是否等于 0
        log.info("remove before user: {}", userService.getOne(new UpdateWrapper<User>().lambda().eq(User::getName, "Tirias")));
        // 删除不会执行真正的删除, 只会修改 isDeleted 为 1
        userService.remove(new UpdateWrapper<User>().lambda().eq(User::getName, "Tirias"));
        // 由于逻辑删除将 isDeleted 修改为 1, 查询将无法查出这条数据
        log.info("remove afterwards user: {}", userService.getOne(new UpdateWrapper<User>().lambda().eq(User::getName, "Tirias")));
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
