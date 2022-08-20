package com.atguigu.servelt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet是sun公司定制的标准，用于处理客户端的请求
 * tomcat实现了Servlet标准
 * httpservlet就是tomcat提供的servlet实例
 *
 * @author Vanas
 * @create 2020-04-30 2:31 下午
 */
public class RegistServlet extends HttpServlet {
    /**
     * 处理具体请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过request(请求对象)获取客户端提交的数据

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username + "==" + password);

        //2. 将数据写入到数据库
        // JDBC

        //3.通过 response对象给客户端响应
        resp.getWriter().println("success！");
    }
}
