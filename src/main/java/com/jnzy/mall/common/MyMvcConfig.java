package com.jnzy.mall.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展SpringMVC的功能
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override //这里配置静态资源访问路径
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/image/");


    }

    //无业务逻辑跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //项目运行后直接访问或访问index.html（不存在index.html页面）界面跳转到login登录界面
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/toLogin.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("login");
        registry.addViewController("/toregister.html").setViewName("register");

    }



}
