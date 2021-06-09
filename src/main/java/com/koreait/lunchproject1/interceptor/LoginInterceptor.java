package com.koreait.lunchproject1.interceptor;

import com.koreait.lunchproject1.controller.MyUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();


        if(ObjectUtils.isEmpty(MyUtils.getLoginUser(session))){
            response.sendRedirect("/user/login.go");
            return false;
        }else{
            session.setMaxInactiveInterval(1800);
            return true;
        }

    }

}
