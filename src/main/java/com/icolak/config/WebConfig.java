package com.icolak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Since we just want to get(view) the page, we are not passing any data,
    // we create endpoints and the views corresponding to those endpoints here(Static Pages)
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
    }

}
