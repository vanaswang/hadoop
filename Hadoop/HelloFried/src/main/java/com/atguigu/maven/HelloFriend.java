package com.atguigu.maven;

import com.atguigu.mavin.Hello;

/**
 * @author Vanas
 * @create 2020-03-30 7:43 下午
 */
public class HelloFriend {
    public String sayHelloToFriend(String name) {
        Hello hello = new Hello();
        String str = hello.sayHello(name) + " I am " + this.getMyName();
        return str;
    }

    public String getMyName() {
        return "songsong";
    }
}
