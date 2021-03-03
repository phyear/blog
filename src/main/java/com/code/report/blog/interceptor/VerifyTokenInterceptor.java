package com.code.report.blog.interceptor;

import com.code.report.blog.annotation.VerifyToken;
import com.code.report.blog.infra.dto.UserDTO;
import com.code.report.blog.infra.exception.CommonException;
import com.code.report.blog.infra.util.JWTTokeUtil;
import com.code.report.blog.infra.util.RedisUtil;
import com.code.report.blog.infra.util.SpringUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhaotianxin
 * @date 2021-02-07 10:20
 */
public class VerifyTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取到目标方法对象
        HandlerMethod method = (HandlerMethod) handler;
        //取到方法上的注解
        VerifyToken verifyToken = method.getMethodAnnotation(VerifyToken.class);
        // 校验token
        if (!ObjectUtils.isEmpty(verifyToken) && Boolean.TRUE.equals(verifyToken.verify())) {
            String authorization = request.getHeader("Authorization");
            if (ObjectUtils.isEmpty(authorization)) {
                throw new CommonException("error.authorization.illegal");
            }
            String suffix = "Bearer ";
            String token = authorization.substring(suffix.length());
            UserDTO userDTO = JWTTokeUtil.decodeJWT(token);
            // 从redis中获取指定Token,没有就从新登陆
            Object tokens = SpringUtil.getBean(RedisUtil.class).get("token:" + userDTO.getId());
            if(ObjectUtils.isEmpty(tokens)){
                throw new CommonException("error.authorization.illegal");
            }
        }
        return true;
    }
}
