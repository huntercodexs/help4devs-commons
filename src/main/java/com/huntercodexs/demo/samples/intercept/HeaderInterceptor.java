package com.huntercodexs.demo.samples.intercept;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Captura todos os headers da requisição
        Map<String, String> headers = extractHeaders(request);

        // Exemplo: logar os headers
        System.out.println("==== HEADERS DA REQUISIÇÃO ====");
        headers.forEach((key, value) -> System.out.println(key + ": " + value));

        // Caso precise, você pode armazenar no contexto (ThreadLocal, MDC, etc.)
        // para reutilizar em outras partes da aplicação

        return true; // continuar o fluxo normal da requisição
    }

    private Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, request.getHeader(name));
        }

        return headers;
    }
}

