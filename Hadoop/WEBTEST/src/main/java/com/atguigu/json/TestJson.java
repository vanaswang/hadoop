package com.atguigu.json;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * @author Vanas
 * @create 2020-04-30 3:21 下午
 */
public class TestJson {

    @Test
    public void JavaToJson() {
        User user = new User();
        user.setId(1001);
        user.setUsername("Admin");
        user.setPassword("nicai");

        // Java-->Json
        Gson gson = new Gson();

        String jsonStr = gson.toJson(user);
        System.out.println("jsonStr = " + jsonStr);
        // {"id":1001,"username":"Admin","password":"nicai"}
    }


    @Test
    public void testJsonToJava() {
        String jsonStr = "{\"id\":1001,\"username\":\"Admin\",\"password\":\"nicai\"}";

        Gson gson = new Gson();
        User user = gson.fromJson(jsonStr, User.class);
        System.out.println("user = " + user);
    }
}

class User {
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}