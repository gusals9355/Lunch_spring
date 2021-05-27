package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.BoardDAO;
import com.koreait.lunchproject1.model.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {

    @Autowired
    BoardDAO boardDAO;

    @RequestMapping(value="/write", method= RequestMethod.GET)
    public String boardWrite(Model model, HttpSession session){

//        if(MyUtils.getLoginUser(session) == null){
//            return "redirect:/user/login";
//            //redirect : GET
//            //forward : POST
//        }

        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
        model.addAttribute("typelist",typelist);
        MyUtils.setTemplate(model,"글 등록 | 오늘 점심 뭐먹지?","board/write",session);
        return MyUtils.TEMPLATE;
    }

    @RequestMapping(value="/write", method= RequestMethod.POST)
    public String boardWrite(MultipartFile[] file, BoardVO vo, HttpServletRequest request) throws IOException {

        String uploadPath = "D:\\JavaBackendClass\\LunchProject4\\spring\\src\\main\\webapp\\upload";

        List<String> list = new ArrayList<>();
        for (MultipartFile m:file) {
            list.add(m.getOriginalFilename());
        }

        for(int i=0; i< file.length; i++){
            File target = new File(uploadPath, list.get(i));

            try {
                FileCopyUtils.copy(file[i].getBytes(), target);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        vo.setId("123");
        String fileName="";
        for (String s:list) {
            fileName += s+",";
        }
        fileName = fileName.substring(0, fileName.length()-1);

        vo.setPicture(fileName);
        boardDAO.insertBoard(vo);

        return "redirect:/ojm";
    }

}