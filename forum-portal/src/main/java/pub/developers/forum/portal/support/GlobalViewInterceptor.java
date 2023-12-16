package pub.developers.forum.portal.support;

import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pub.developers.forum.api.model.ResultModel;
import pub.developers.forum.api.response.user.UserInfoResponse;
import pub.developers.forum.api.service.MessageApiService;
import pub.developers.forum.api.service.UserApiService;
import pub.developers.forum.common.constant.Constant;
import pub.developers.forum.common.enums.ErrorCodeEn;
import pub.developers.forum.common.exception.BizException;
import pub.developers.forum.common.support.AesUtils;
import pub.developers.forum.common.support.GlobalViewConfig;
import pub.developers.forum.common.support.RequestContext;
import pub.developers.forum.common.support.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiangqiang.Bian
 * @create 2020/10/29
 * @desc
 **/
@Slf4j
@Component
public class GlobalViewInterceptor extends HandlerInterceptorAdapter {

    @Value("${aes-secret.key}")
    private String key;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestContext.init();

        String sid = WebUtil.cookieGetSid(request);
        if (ObjectUtils.isEmpty(sid)) {
            log.info("{} the sid is empty", RequestContext.getTraceId());
            throw new BizException(ErrorCodeEn.USER_NOT_LOGIN);

        }

        if (!ObjectUtils.isEmpty(sid)) {
            //String userStr = AesUtils.decrypt(sid, key);
            String userStr = sid;
            if (StringUtils.isBlank(userStr)) {
                log.info("{} dectypt the sid return is empty", RequestContext.getTraceId());
                throw new BizException(ErrorCodeEn.USER_NOT_LOGIN);

            }
            WebContext.setCurrentSid(sid);
            WebContext.setCurrentUser(JSON.parseObject(userStr, UserInfoResponse.class));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            WebContext.removeAll();
            RequestContext.removeAll();

    }

}
