package com.koreait.lunchproject1.interceptor;

import com.koreait.lunchproject1.controller.MyUtils;
import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class RankedUpInterceptor implements HandlerInterceptor {
    @Autowired
    MemberDAO memberDAO;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        MemberVO memberVO = new MemberVO();
        memberVO = MyUtils.getLoginUser(session);
        System.out.println("gogo");
        if(memberVO == null) return;
        int point = memberDAO.getUserInfo(memberVO).getPoint();
        memberVO.setId(memberVO.getId());
        memberVO.setPoint(point);

        memberDAO.modRanked(memberVO);
        MyUtils.reUserInfo(session,memberDAO);
    }
}
