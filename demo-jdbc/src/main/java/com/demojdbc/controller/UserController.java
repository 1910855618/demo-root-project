package com.demojdbc.controller;

import com.demojdbc.dao.UserDao;
import com.demojdbc.model.dto.UserDTO;
import com.demojdbc.model.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Instant;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserDao userDao;

    @PostMapping("/save")
    public ResponseVO save(@RequestBody UserDTO userDTO) {
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").data(userDao.save(userDTO)).build();

    }

    @PostMapping("/change")
    public ResponseVO change(@RequestBody UserDTO userDTO) {
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").data(userDao.change(userDTO)).build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseVO deleteById(@PathVariable int id) {
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").data(userDao.deleteById(id)).build();
    }

    @GetMapping("/queryAll")
    public ResponseVO queryAll() {
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").data(userDao.queryAll()).build();
    }

    @GetMapping("/get")
    public ResponseVO getUserById(int id) {
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").data(userDao.getUserById(id)).build();
    }
}
