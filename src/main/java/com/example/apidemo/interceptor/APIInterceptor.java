package com.example.apidemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class APIInterceptor extends HandlerInterceptorAdapter {
    private Map<String, String> handlerMap = new ConcurrentHashMap<>();
    public static final String API_PREFIX = "/api";
    public static final String URI_PATTERN = API_PREFIX + "/\\w+";
    private Pattern urlPattern;
    @PostConstruct
    public void init() {
        urlPattern = Pattern.compile(URI_PATTERN);
        handlerMap.put("h1", "HELLO-WORLD");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre handle");
        StringBuffer requestURL = request.getRequestURL();
        log.info("request URL: {}", requestURL);
        String requestURI = request.getRequestURI();
        log.info("request URI: {}", requestURI);

        Matcher matcher = urlPattern.matcher(requestURI);
        if (matcher.find()) {
            int index = requestURI.indexOf(API_PREFIX) + API_PREFIX.length() + 1;
            log.info("{} index of {} is : {}", API_PREFIX, requestURI, index);
            String key = requestURI.substring(index);
            log.info("matched, do handle, key : {}", key);
            response.getWriter().write(handlerMap.getOrDefault(key, "api not reg"));
            return false;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("post handle");
        super.postHandle(request, response, handler, modelAndView);
    }
}
