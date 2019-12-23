package com.xiaozhu.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//也可以implements HandlerInterceptor 但是必须实验 preHandle postHandle afterCompletion


@Component
public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception{
        String uri = request.getRequestURI();
        System.out.println(uri);
        if(uri.equals("/app/interceptor")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mv){
        System.out.println("after test");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object o,Exception e){}{
        System.out.println("afterCompletion");
    }
}
