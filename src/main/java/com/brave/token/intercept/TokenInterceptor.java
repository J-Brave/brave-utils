package com.brave.token.intercept;

import com.alibaba.fastjson.JSONObject;
import com.brave.token.anno.NoAuthorization;
import com.brave.token.domain.User;
import com.brave.token.util.UserThreadLocal;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jbrave
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (((HandlerMethod) handler).hasMethodAnnotation(NoAuthorization.class)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            User user = new User();
            UserThreadLocal.set(user);
            return true;
        }

        JSONObject rsp = new JSONObject();
        rsp.put("errCode", "401");
        rsp.put("message", "无权限");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(rsp.toString());
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
