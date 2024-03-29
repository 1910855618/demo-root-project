package com.demoknife4j.controller;

import com.demoknife4j.model.vo.ResponseVO;
import com.demoknife4j.model.vo.UserVO;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Random;

@Log4j2
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @ApiOperation(value = "添加用户", notes = "作用于添加新用户", tags = "用户模块", httpMethod = "post", response = ResponseVO.class)
    @ApiImplicitParam(name = "userVO", value = "用户 ID", dataTypeClass = UserVO.class, paramType = "body", required = true)
    @PostMapping("/add")
    public ResponseVO add(@RequestBody UserVO userVO) {
        return ResponseVO.builder()
                .message("success")
                .code(200)
                .timestamp(Instant.now().toEpochMilli())
                .data(UserVO.builder()
                        .name(userVO.getName())
                        .age(userVO.getAge())
                        .userId(new Random().nextInt())
                        .phone(userVO.getPhone())
                        .email(userVO.getEmail())
                        .build())
                .build();
    }

    @ApiOperation(value = "根据 id 删除用户", notes = "作用于删除以添加用户")
    @DeleteMapping("/delete/{userId}")
    @ApiImplicitParam(name = "userId", value = "用户 ID", required = true, dataTypeClass = Integer.class, paramType = "path")
    public ResponseVO delete(@PathVariable Integer userId) {
        log.info("delete user, userId: {}", userId);
        return ResponseVO.builder()
                .message("success")
                .code(200)
                .timestamp(Instant.now().toEpochMilli())
                .data(UserVO.builder().name("Anna").age(24).userId(userId).phone("15848784444").email("Anna@qq.com").build())
                .build();
    }

    @ApiOperation(value = "根据 name 查询用户", notes = "作用于查询指定用户名的用户")
    @ApiImplicitParam(name = "name", value = "姓名", dataTypeClass = String.class, paramType = "query", required = true, defaultValue = "null", example = "Anna", allowableValues = "1~20")
    @GetMapping("/getUser")
    public ResponseVO getUser(@RequestParam String name) {
        log.info("get user by name, name: {}", name);
        return ResponseVO.builder()
                .message("success")
                .code(200)
                .timestamp(Instant.now().toEpochMilli())
                .data(UserVO.builder().name(name).age(24).userId(new Random().nextInt()).phone("15848784444").email("Anna@qq.com").build())
                .build();
    }

    @ApiOperation(value = "根据用户 ID 修改用户信息", notes = "作用于修改指定 ID 的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户 ID", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "name", value = "姓名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "age", value = "年龄", dataTypeClass = Integer.class, required = true)
    })
    @PutMapping("/change")
    public ResponseVO change(@RequestParam Integer userId, @RequestParam String name, @RequestParam Integer age) {
        return ResponseVO.builder()
                .message("success")
                .code(200)
                .timestamp(Instant.now().toEpochMilli())
                .data(UserVO.builder().userId(userId).name(name).age(age).phone("15844457484").email("Anna@qq.com").build())
                .build();
    }

    @ApiOperation(value = "查询年龄为输入年龄的用户", notes = "作用于查询年龄为输入年龄的用户")
    @GetMapping("/getUserByAge")
    public ResponseVO getUserByAge(
            @ApiParam(name = "age", value = "年龄", defaultValue = "0", allowableValues = "0~200", required = true, example = "18")
            @RequestParam
            Integer age) {
        return ResponseVO.builder()
                .message("success")
                .code(200)
                .data(UserVO.builder().userId(new Random().nextInt()).name("Anna").age(age).phone("15844457484").email("Anna@qq.com").build())
                .data(null)
                .build();
    }
}
