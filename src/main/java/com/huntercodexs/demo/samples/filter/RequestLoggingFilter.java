//package com.huntercodexs.demo.samples.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class RequestLoggingFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
//
//        // continua o fluxo
//        filterChain.doFilter(wrappedRequest, response);
//
//        // só depois da execução do controller o corpo estará em cache
//        byte[] body = wrappedRequest.getContentAsByteArray();
//        if (body.length > 0) {
//            String requestBody = new String(body, StandardCharsets.UTF_8);
//            System.out.println("Request Body: " + requestBody);
//        }
//    }
//}
//
