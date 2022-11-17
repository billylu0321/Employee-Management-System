package com.billy.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    //resolve the request
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //get the language locale in the request
        String language = request.getParameter("la");
        Locale locale = Locale.getDefault();//if there is no specific language locale, just use the default locale
        //if there is specific local info, such as en_US or zh_CN, the just create a new locale and return
        if(!StringUtils.isEmpty(language)){
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
