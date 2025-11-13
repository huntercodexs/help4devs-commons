//package com.huntercodexs.demo.samples.intercept;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//import static java.util.Collections.list;
//import static java.util.stream.Collectors.toMap;
//
//@Component
//public class PersistePosvendaIntercept implements HandlerInterceptor {
//
//    private static final Logger log = LoggerFactory.getLogger(PersistePosvendaIntercept.class);
//    private static final String CORRELATION_HEADER = "X-Brad-CorrelationId";
//    private static final String MDC_KEY = "correlationId";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        geraOuCapturaCorrelationId(request);
//
//        final var metodo = request.getMethod();
//        final var uri = request.getRequestURI();
//        final var query = request.getQueryString();
//        final var urlCompleta = query == null ? uri : String.format("%s?%s", uri, query);
//        final var headers = list(request.getHeaderNames())
//                .stream()
//                .collect(toMap(h -> h, h -> list(request.getHeaders(h))));
//
//        //byte[] inputStreamBytes = StreamUtils.copyToByteArray(request.getInputStream());
//        //Map<String, String> jsonRequest = new ObjectMapper().readValue(inputStreamBytes, Map.class);
//        //String requestBodyJsonString = jsonRequest.get("body");
//
//        //log.info("Requisição recebida: metodo={}, url={}, headers={}, corpo={}", metodo, urlCompleta, headers, requestBodyJsonString);
//        log.info("Requisição recebida: metodo={}, url={}, headers={}", metodo, urlCompleta, headers);
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // No-op
//    }
//
//    private static void geraOuCapturaCorrelationId(HttpServletRequest request) {
//
//        final var correlationId = Optional.ofNullable(request.getHeader(CORRELATION_HEADER))
//                .filter(id -> !id.isBlank())
//                .orElse(UUID.randomUUID().toString());
//
//        MDC.put(MDC_KEY, correlationId);
//    }
//}
