//package com.huntercodexs.demo.samples.intercept;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class PersistePosVendaConfig implements WebMvcConfigurer {
//
//    private final PersistePosvendaIntercept persistePosvendaIntercept;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(persistePosvendaIntercept).addPathPatterns("/**");
//    }
//}
