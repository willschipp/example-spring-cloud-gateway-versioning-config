package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test","native"})
public class ApplicationIT {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        assertTrue(applicationContext.getBeanDefinitionCount() > 0);
    }

}