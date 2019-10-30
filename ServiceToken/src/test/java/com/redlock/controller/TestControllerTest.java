package com.redlock.controller;

import com.redlock.util.RedissLockUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTest {



    @org.junit.Test
    public void getCurrentDate() {

    }

    @org.junit.Test
    public void testlock() {

        RedissLockUtil.lock("test111");
        RedissLockUtil.lock("testlock", TimeUnit.MINUTES, 10);

    }
}
