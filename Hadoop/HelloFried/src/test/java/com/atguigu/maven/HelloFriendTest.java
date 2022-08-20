package com.atguigu.maven;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Vanas
 * @create 2020-03-30 7:52 下午
 */
public class HelloFriendTest {
    @Test
    public void testHelloFriend() {
        HelloFriend helloFriend = new HelloFriend();
        String results = helloFriend.sayHelloToFriend("honghong");
        System.out.println(results);
        assertEquals("Hello honghong! I am songsong",results);
    }
}
