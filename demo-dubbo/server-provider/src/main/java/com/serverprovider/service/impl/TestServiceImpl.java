package com.serverprovider.service.impl;

import com.commonapi.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class TestServiceImpl implements TestService {
    @Override
    public String hello(String message) {
        return String.join("", "hello, ", message);
    }
}
