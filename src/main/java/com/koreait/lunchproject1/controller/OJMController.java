package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.BoardDAO;
import com.koreait.lunchproject1.model.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class OJMController {

    @Autowired
    BoardDAO boardDAO;
    @Autowired
    MemberDAO memberDAO;
    @GetMapping("/ojm")
    public String ojm(Model model, HttpSession session) {
        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
        String path = new File("").getAbsolutePath();
        System.out.println(path);
        model.addAttribute("typelist",typelist);
        model.addAttribute("list", boardDAO.getAllBoard());
        MyUtils.setTemplate(model, "오늘 점심은 뭐먹지?", "/ojm", session);
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
