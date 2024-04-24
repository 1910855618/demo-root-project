package com.demolombok;

import com.demolombok.entity.Point;
import com.demolombok.entity.Student;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;

@Slf4j
@SpringBootTest
class DemoLombokApplicationTests {

    @Test
    void lombokLogTest() {
        log.debug("debug: {}. {}", "test", "debug 通常不会显示，需要在 application.yml 中开启 logging.level.root: debug");
        log.info("info: {}", "test");
        log.warn("warning: {}", "test");
        Exception e = new RuntimeException("error test!");
        log.error("error: {}", e.getMessage(), e);
    }

    @Test
    void lombokGetterSetterTest() {
        Student student = new Student();
        student.setName("Annie");
        student.setSex("girl");
        student.setAge(16);
        log.info("name: {}, sex: {}, age: {}", student.getName(), student.getSex(), student.getAge());
    }

    @Test
    void lombokToStringTest() {
        Student student = new Student();
        student.setName("Annie");
        student.setSex("girl");
        student.setAge(16);
        log.info("student: {}", student.toString());
    }

    @Test
    void lombokEqualsAndHashCodeTest() {
        Student student = new Student();
        student.setName("Annie");
        student.setSex("girl");
        student.setAge(16);
        Student student2 = new Student();
        student2.setName("Annie");
        student2.setSex("girl");
        student2.setAge(17);
        log.info("student equals: {}, hashCode: {}", student.equals(student2), student.hashCode());
    }

    @Test
    void lombokConstructorTest() {
        new Student();
        new Student("Jack", "boy");
        new Student("Jack", "boy", 18);
    }

    @Test
    void lombokBuilderTest() {
        Student.builder().name("Annie").sex("girl").age(14).build();
    }

    @Test
    void lombokValueTest() {
        Point point = new Point(1, 2);
        Point point1 = new Point(point.getX()+3, point.getY()+1);
        log.info("location: {}, location1: {}", point, point1);
    }

    @Test
    @SneakyThrows
    void lombokSneakyThrowsTest() {
        InetAddress inetAddress = InetAddress.getLocalHost();
    }
}
