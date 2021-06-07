package com.koreait.lunchproject1.controller;

import com.google.gson.Gson;
import com.koreait.lunchproject1.model.dao.BoardDAO;
import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.vo.BoardVO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.ssl.CookieExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OJMController {

    @Autowired
    BoardDAO boardDAO;
    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/ojm")
    public String ojm(Model model, HttpSession session, HttpServletResponse response) {
        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
        Cookie cookie = new Cookie("view",null); //조회수에 사용될 쿠키
        cookie.setComment("게시판 조회 확인");
        cookie.setMaxAge(86400); // 쿠키유효시간 1일 60*60*24
        response.addCookie(cookie);
//        String path = new File("").getAbsolutePath();
        model.addAttribute("typelist",typelist);
        model.addAttribute("list", boardDAO.getAllBoard());
        MyUtils.setTemplate(model, "오늘 점심은 뭐먹지?", "ojm", session);
        return MyUtils.TEMPLATE;
    }
    @GetMapping("/regi_manager.do")
    public String regi_manager(Model model, HttpSession session){

        MyUtils.setTemplate(model,"오늘 점심은 뭐먹지?","/register_manager",session);
        return MyUtils.TEMPLATE;
    }

    @ResponseBody
    @PostMapping("/regi_manager.do")
    public Map<String,String> regi_managerP(@RequestBody MemberVO vo, HttpSession session){
        System.out.println(vo.getCode());
        Map<String,String> map = new HashMap<>();
        String c = memberDAO.regiManager(vo);
        if(c == null){
            map.put("result","0");
            return map;
        }else{
            map.put("result","1");
            memberDAO.modManager(MyUtils.getLoginUserID(session));
            MyUtils.reUserInfo(session,memberDAO);
            return map;
        }
    }
}