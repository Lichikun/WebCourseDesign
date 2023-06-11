package com.example.petshop.common.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.petshop.common.config.SkipTokenValidation;
import com.example.petshop.common.exception.MyException;
import com.example.petshop.common.utils.JwtTokenProvider;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //页面渲染完成之前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                          Object handler) throws Exception {

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = request.getHeader("token");

        //如果是方法探测，直接通过
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        //如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(SkipTokenValidation.class)) {
            //如果有则跳过认证检查
            SkipTokenValidation passToken = method.getAnnotation(SkipTokenValidation.class);
            if (passToken.required()) {
                return true;
            }
        }
        //进行token验证
        if(token == null || token.length() == 0){
            throw new MyException("token is Empty");
        }else{
           if(!jwtTokenProvider.validateToken(token))
                throw new MyException("token Mistake");
        }
        return true;
    }

    // 目标方法执行之后
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    // 页面渲染完成之后
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, @Nullable Exception ex) throws Exception {
    }
}
