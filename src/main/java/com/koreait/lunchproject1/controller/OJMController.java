package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class OJMController {

    @Autowired
    BoardDAO boardDAO;

    @RequestMapping(value = "/ojm", method = RequestMethod.GET)
    public String ojm(Model model, HttpSession session) {
        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
        model.addAttribute("typelist",typelist);
        model.addAttribute("list", boardDAO.getAllBoard());
        MyUtils.setTemplate(model, "오늘 점심은 뭐먹지?", "/ojm", session);
        return MyUtils.TEMPLATE;
    }

}
