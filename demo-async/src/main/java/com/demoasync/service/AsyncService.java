package com.demoasync.service;

import java.util.concurrent.Future;

public interface AsyncService {
    Future<String> asyncMethod();

    void syncMethod();
}
