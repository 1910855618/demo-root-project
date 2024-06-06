package com.serverprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.commonapi.service.TestService;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String hello(String message) {
        return String.join("", "hello, ", message);
    }
}
