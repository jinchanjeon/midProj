package kr.or.ddit.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CommonFilter 초기화");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 요청 전처리
        System.out.println("CommonFilter 요청 전처리");

        // 다음 필터 또는 서블릿 호출
        chain.doFilter(request, response);

        // 응답 후처리
        System.out.println("CommonFilter 응답 후처리");
    }

    @Override
    public void destroy() {
        System.out.println("CommonFilter 소멸");
    }
}
