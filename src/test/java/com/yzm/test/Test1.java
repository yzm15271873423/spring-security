package com.yzm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 17:07
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {

    @Test
    public void test1(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String s = encoder.encode("lisi");
        System.out.println(s);


       /* boolean result = encoder.matches("pwd", s);
        System.out.println(result);*/
    }
}
