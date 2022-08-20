package com.atguigu.maven;

import com.atguigu.mavin.Hello;
import org.junit.Test;

/**
 * @author Vanas
 * @create 2020-03-30 7:35 下午
 */
public class HelloTest {
    @Test
    public void testHello() {
        Hello hello = new Hello();
        String maven = hello.sayHello("Maven");
        System.out.println(maven);
    }

}
