package com.billy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展springmvc的方法
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    /*
    add a custom view controller. For example, if input /index.html in the address field, then it will
    jump to index page; and if input /main.html in the address field, then it will jump to dashboard page
    */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        /*
        by setting the view controller, to some extent it can solve the security issue, customer see the address is
        /main.html, but real page is dashboard
         */
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //customize the i18n component
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //register the customized interceptor into Bean, excludePathPatterns means those address will not be intercepted
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/css/**","/js/**","/img/**");
    }
}
