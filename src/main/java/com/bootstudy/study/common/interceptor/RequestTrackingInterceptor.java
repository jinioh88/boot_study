package com.bootstudy.study.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Slf4j
public class RequestTrackingInterceptor extends BaseHandlerInterceptor {
    private static final ThreadLocal<Long>  startTImeHolder = new ThreadLocal<>();
    private static final String HEADER_X_TRACK_ID = "X-Track-Id";
    private final Random random = new Random(1233);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        val beforeNanoSec = System.nanoTime();
        startTImeHolder .set(beforeNanoSec);

        val trackId = getTrackId(request);
        MDC.put(HEADER_X_TRACK_ID,  trackId);
        response.setHeader(HEADER_X_TRACK_ID, trackId);

        return true;
    }

    private String getTrackId(HttpServletRequest request) {
        String trackId = request.getHeader(HEADER_X_TRACK_ID);
        if(trackId == null) {
            int seed = Integer.MAX_VALUE;
            trackId = String.valueOf(random.nextInt(seed));
        }
        return trackId;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        val beforeNonoSec = startTImeHolder.get();

        if(beforeNonoSec == null) {
            return;
        }

        val epapseNanoSec = System.nanoTime() - beforeNonoSec;
        log.info("path={}, method={} Elapsed {}ms.", request.getRequestURI(), request.getMethod(), epapseNanoSec);

        startTImeHolder.remove();;
    }
}
