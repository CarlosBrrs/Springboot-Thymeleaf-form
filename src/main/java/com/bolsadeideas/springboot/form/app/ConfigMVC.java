package com.bolsadeideas.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Para registrar el ElapsedTimeInterceptor en la configuración de Spring
@Configuration
public class ConfigMVC implements WebMvcConfigurer {

    @Autowired
    @Qualifier("elapsedTimeInterceptor") //En caso que la interfaz tenga más de una implementación registrada en Spring
    private HandlerInterceptor elapsedTimeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(elapsedTimeInterceptor);
    }
}
