package com.demopool2.manager;

import com.demopool2.entity.MyObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MyObjectManager {
    @Resource
    private GenericObjectPool<MyObject> myObjectPool;

    public void printStr() {
        MyObject myObject = null;
        try {
            // 借用连接池中的对象
            myObject = myObjectPool.borrowObject();
            // 使用对象
            myObject.setName("luhang");
            log.info("借用到的对象：{}", myObject);
        } catch (Exception e) {
            log.error("向连接池借用对象失败：", e);
        } finally {
            if(myObject != null) {
                // 向连接池归还对象
                myObjectPool.returnObject(myObject);
            }
        }
    }
}
