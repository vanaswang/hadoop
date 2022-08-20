package com.atguigu.controller;

import com.atguigu.beans.User;
import com.atguigu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制层，用户处理客户端的请求， 以及给客户端完成响应
 *
 * @author Vanas
 * @create 2020-04-30 7:16 下午
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @RequestMapping("/regist") ： 将客户端的 regist请求映射到当前方法
     * @ResponseBody 将方法的返回通过json的格式返回给客户端
     * <p>
     * 方法的User参数用来接收客户端提交的参数，只需要保证客户端的参数名与User的属性名一致，
     * 就能直接将客户端提交的参数封装到user对象中
     */
    @ResponseBody
    @RequestMapping("/regist")
    public String doRegist(User user) {
        System.out.println("user = " + user);

        //调用service的方法将数据传入到service
        int i = userService.registUser(user);
        if (i == 1) {
            return "regist success";
        }
        return "regist Error";
    }
}
