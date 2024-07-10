package com.demopool2.factory;

import com.demopool2.entity.MyObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyObjectFactory implements PooledObjectFactory<MyObject> {
    // 激活对象
    @Override
    public void activateObject(PooledObject<MyObject> pooledObject) throws Exception {
        log.info("已激活对象：{}", pooledObject.getObject());
    }

    // 销毁对象
    @Override
    public void destroyObject(PooledObject<MyObject> pooledObject) throws Exception {
        // 销毁对象
        MyObject myObject = pooledObject.getObject();
        String uid = myObject.getUid();
        myObject.destroy();
        log.info("对象已达到回收阈值 {} 对象已被回收", uid);
    }

    // 生成对象
    @Override
    public PooledObject<MyObject> makeObject() throws Exception {
        // 没有可用对象，对象池生成新对象
        MyObject myObject = new MyObject();
        // 初始化对象
        myObject.initialize();
        log.info("对象池无可用空闲对象，生成新对象：{}", myObject);
        return new DefaultPooledObject<>(myObject);
    }

    // 钝化对象
    @Override
    public void passivateObject(PooledObject<MyObject> pooledObject) throws Exception {
        // 还原对象初始化状态，方便下次使用
        MyObject myObject = pooledObject.getObject();
        myObject.setName(null);
        log.info("对象池已将归还的对象钝化：{}", myObject);
    }

    // 验证对象
    @Override
    public boolean validateObject(PooledObject<MyObject> pooledObject) {
        // 验证对象是否有效
        MyObject myObject = pooledObject.getObject();
        log.info("对象池正在验证对象的有效性：{}", myObject);
        return myObject.isValid();
    }
}
