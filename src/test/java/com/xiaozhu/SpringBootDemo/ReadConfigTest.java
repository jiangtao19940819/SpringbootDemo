package com.xiaozhu.SpringBootDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadConfigTest {

    @Value("${name}")
    private String name;

    //spring.profiles.active=test
    @Value("${url}")
    private String url;

    @Test
    public void testReadConfigOne(){

        System.out.println(name);
        System.out.println(url);
    }
}
