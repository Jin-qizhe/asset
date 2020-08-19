package com.ydt.configure;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 用于存储排除拦截的url  （登录/login.html, /css,/js,/img）
     */
    private List<String> urls = new ArrayList<String>();

    /**
     * 进入控制器之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("username"));
        if (session.getAttribute("username") != null) {
            //已登录，放行。。
            return true;
        } else {
            System.out.println("你还没登录，没有权限");
            //未登录，拦截 返回login
            response.sendRedirect("/assets");    //未登录，拦截跳转到登录页
            return false;

        }
    }


    /*设置能通过的url*/
    public List<String> getUrls() {
        urls.add("/assets");   //login url请求
        urls.add("/assets/*");
        urls.add("/insert.html");
        urls.add("/mydetail.html");
        urls.add("/otherdetail.html");
        urls.add("/listrecord.html");

        //静态资源
        urls.add("/layui/**");
        /*urls.add("/js/*");
        urls.add("/css/*");*/
        return urls;
    }
}