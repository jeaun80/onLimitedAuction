package com.example.onlimitedauction.global.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMvcConfig implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Users/gimjingwon/Documents/onLimitedAuction/src/main/resources/static/image/**") // --1
                .addResourceLocations("file:///users/gimjingwon/Documents/onLimitedAuction/src/main/resources/static/image/"); //--2
    }

}