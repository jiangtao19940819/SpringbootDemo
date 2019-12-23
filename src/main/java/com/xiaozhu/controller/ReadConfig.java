package com.xiaozhu.controller;

import com.xiaozhu.bean.Book;
import com.xiaozhu.bean.MyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class ReadConfig {
    @Value("${name}")
    private String name;

    //spring.profiles.active=test
    @Value("${url}")
    private String url;

    @Autowired
    private Book book;

    @Autowired
    private MyBook myBook;

    @GetMapping("/readconfigone")
    public void testValue(){
        System.out.println(name);
        System.out.println(url);
    }

//    将配置文件中配置的每一个属性的值，映射到这个组件中
//    * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
//            * prefix = "book"：配置文件中哪个下面的所有属性进行一一映射
//    * @Component只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；


    @GetMapping("/readconfigtwo")
    public String testCongurationProperties(){
        System.out.println(book);
        return book.toString();
    }

//    　加载指定的属性文件（*.properties）到 Spring 的 Environment 中。可以配合 @Value 和 @ConfigurationProperties 使用。
//
//    @PropertySource 和 @Value 组合使用，可以将自定义属性文件中的属性变量值注入到当前类的使用@Value注解的成员变量中。
//    @PropertySource 和 @ConfigurationProperties 组合使用，可以将属性文件与一个Java类绑定，将属性文件中的变量值注入到该Java类的成员变量中。

    @GetMapping("/readconfigthree")
    public  MyBook testPropertySource(){
        System.out.println("readconfigthree");
        return myBook;
    }

    @GetMapping("/hello")
    public void isException() throws Exception{
      int num = 10/0;
    }

    @GetMapping("getconfig")
    public Object getGlobalConfig(Model model){
        Map<String,Object> globalConfig = model.asMap();
        System.out.println(globalConfig);
        return globalConfig.get("userInfo");

    }

    @GetMapping("/app/interceptor")
    public void testInterceptor(){
       System.out.println("hello interceptor");
       //return new ModelAndView("test");

    }
}
