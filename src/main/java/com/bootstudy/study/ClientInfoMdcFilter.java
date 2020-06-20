package com.bootstudy.study;

import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientInfoMdcFilter extends OncePerRequestFilter {
    private static final String FORWARD_FOR_HEADER = "X-Forwared-For";

    private final MessageSource messageSource;

    // 공통 처리를 구현하는 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String remoteIp = Optional.ofNullable(
                request.getHeader(FORWARD_FOR_HEADER)).orElse(request.getRemoteAddr());
        MDC.put(FORWARD_FOR_HEADER, remoteIp);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(FORWARD_FOR_HEADER);
        }
    }
}
