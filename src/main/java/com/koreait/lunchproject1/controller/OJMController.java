package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.BoardDAO;
import com.koreait.lunchproject1.model.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.ssl.CookieExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class OJMController {

    @Autowired
    BoardDAO boardDAO;
    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/ojm")
    public String ojm(Model model, HttpSession session, HttpServletResponse response) {
//        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
//        Cookie cookie = new Cookie("view",null); //조회수에 사용될 쿠키
//        cookie.setComment("게시판 조회 확인");
//        cookie.setMaxAge(86400); // 쿠키유효시간 1일 60*60*24
//        response.addCookie(cookie);
//
//        String path = new File("").getAbsolutePath();
//        System.out.println(path);
//        model.addAttribute("typelist",typelist);
//        model.addAttribute("list", boardDAO.getAllBoard());
        MyUtils.setTemplate(model, "오늘 점심은 뭐먹지?", "ojm", session);
        return MyUtils.TEMPLATE;
    }

    @GetMapping("regi_manager.do")
    public String regi_manager(Model model, HttpSession session){

        MyUtils.setTemplate(model,"오늘 점심은 뭐먹지?","/register_manager",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("regi_manager.do")
    public String regi_managerP(Model model, HttpSession session, @RequestParam(value = "code")String code){
        if(memberDAO.regiManager(code) != null){
            memberDAO.modManager(MyUtils.getLoginUserID(session));
            return MyUtils.REDIRECTPAGE("/ojm");
        }
        //TODO: ajax
        System.out.println("asd");
        return MyUtils.REDIRECTPAGE("/ojm");
    }
}
