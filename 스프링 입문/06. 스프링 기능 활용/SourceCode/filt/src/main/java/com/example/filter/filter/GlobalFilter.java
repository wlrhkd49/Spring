package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Component // 필터 등록 스프링에 의해 빈으로 관리
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);


        chain.doFilter(httpServletRequest, httpServletResponse);


        String url = httpServletRequest.getRequestURI();


        // 후처리
        // req
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request status : {}, requestBody : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatusCode = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse(); // 이것까지 해야 클라이언트가 응답을 받을 수 있다.

        log.info("response status : {}, responseBody : {}", httpStatusCode, resContent);

    }

}
