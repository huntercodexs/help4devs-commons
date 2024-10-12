package com.huntercodexs.demo.config;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class OAuthRequestedMatcher implements RequestMatcher {

//    @Override
//    public boolean matches(HttpServletRequest request) {
//        String auth = request.getHeader("Authorization");
//        return (auth != null) && auth.startsWith("Bearer");
//    }

    @Override
    public boolean matches(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        return (auth != null) &&
                (auth.startsWith("Bearer") || auth.startsWith("Basic")) &&
                (auth.replaceFirst("Bearer|Basic", "").trim().equals("dXNlcm5hbWU6cGFzc3dvcmQ="));
    }
    
}
