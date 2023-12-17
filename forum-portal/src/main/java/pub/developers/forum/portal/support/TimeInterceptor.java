package pub.developers.forum.portal.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {

    ThreadLocal<Date> threadLocal = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
        threadLocal.set(new Date());
        return HandlerInterceptor.super.preHandle(httpServletRequest, response, o);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        Date start = threadLocal.get();
        Date end = new Date();
        log.info("{}:start time{}，end time:{}，total time:{}", request.getRequestURI(), start, end, end.getTime() - start.getTime());
    }















}
