package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.BoardDAO;
import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.dao.RepleDAO;
import com.koreait.lunchproject1.model.vo.BoardVO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import com.koreait.lunchproject1.model.vo.RepleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {

    @Autowired
    BoardDAO boardDAO;
    @Autowired
    RepleDAO repleDAO;
    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/write.do")
    public String boardWrite(Model model, HttpSession session){

        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
        model.addAttribute("typelist",typelist);
        MyUtils.setTemplate(model,"글 등록 | 오늘 점심 뭐먹지?","board/write",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("/write.do")
    public String boardWrite(MultipartFile[] file,HttpSession session, Model model, BoardVO vo, HttpServletRequest request) throws IOException {

        final String uploadPath = "D:/JavaBackendClass/lunchproject1/src/main/resources/static/upload";
        UUID uuid = UUID.randomUUID();

        List<String> list = new ArrayList<>();
        for (MultipartFile m:file) {
            list.add(uuid+"_"+m.getOriginalFilename());
        }

        for(int i=0; i< file.length; i++){
            File target = new File(uploadPath, list.get(i));

            try {
                FileCopyUtils.copy(file[i].getBytes(), target);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        vo.setId(MyUtils.getLoginUserID(session));
        vo.setNickname(MyUtils.getLoginUser(session).getNickname());
        boardDAO.insertBoard(vo);

        for (String s:list) {
            vo.setPicture(s);
            vo.setNo(boardDAO.selBoardNo(vo));
            boardDAO.insertPicture(vo);
        }
        MemberVO memberVO = new MemberVO();
        memberVO.setWritePoint(25);
        memberVO.setId(MyUtils.getLoginUserID(session));
        memberDAO.upPoint(memberVO);
        MyUtils.reUserInfo(session,memberDAO);

        return MyUtils.REDIRECTPAGE("/ojm");
    }

    //TODO: 댓글작업
    @GetMapping("/views.do")
    public String views(Model model, BoardVO boardVO, RepleVO repleVO, HttpSession session, @RequestParam(value = "no") int no){
        repleVO.setNo(no);
        boardVO.setId(MyUtils.getLoginUserID(session));
        model.addAttribute("picture",boardDAO.getPicture(no));
        model.addAttribute("boards",boardDAO.getBoard(boardVO));
        model.addAttribute("reples", repleDAO.getReples(repleVO));
        MyUtils.setTemplate(model,boardVO.getTitle(),"board/views",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("/views.do")
    public String viewsP(Model model, RepleVO repleVO, HttpSession session){
        MemberVO memberVO = MyUtils.getLoginUser(session);
        repleVO.setId(memberVO.getId());
        repleVO.setNickname(memberVO.getNickname());
        repleDAO.insertReple(repleVO);
        return MyUtils.REDIRECTPAGE("/views.do");
    }

    @GetMapping("/delBoard.do")
    public String delBoard(Model model, BoardVO boardVO, HttpSession session, @RequestParam(value = "no") int no){
        boardVO.setNo(no);
        boardVO.setId(MyUtils.getLoginUserID(session));
        boardDAO.delBoard(boardVO);
        MemberVO memberVO = new MemberVO();
        memberVO.setId(MyUtils.getLoginUserID(session));
        memberVO.setWritePoint(25);
        memberDAO.downPoint(memberVO);
        MyUtils.reUserInfo(session,memberDAO);

        return MyUtils.REDIRECTPAGE("/ojm");
    }

    @GetMapping("/modBoard.do")
    public String modBoard(Model model, BoardVO boardVO, HttpSession session){
        final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
        boardVO.setId(MyUtils.getLoginUserID(session));
        model.addAttribute("typelist",typelist);
        model.addAttribute("board",boardDAO.getBoard(boardVO));

        MyUtils.setTemplate(model,boardVO.getTitle(),"board/modBoard",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("/modBoard.do")
    public String modBoardP(MultipartFile[] file,HttpSession session, Model model, BoardVO vo, HttpServletRequest request) throws IOException{
        final String uploadPath = "D:/JavaBackendClass/lunchproject1/src/main/resources/static/upload";
        UUID uuid = UUID.randomUUID();

        List<String> list = new ArrayList<>();
        for (MultipartFile m:file) {
            list.add(uuid+"_"+m.getOriginalFilename());
        }

        for(int i=0; i< file.length; i++){
            File target = new File(uploadPath, list.get(i));

            try {
                FileCopyUtils.copy(file[i].getBytes(), target);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        vo.setId(MyUtils.getLoginUserID(session));
        vo.setNickname(MyUtils.getLoginUser(session).getNickname());
        boardDAO.modBoard(vo);
        boardDAO.delPicture(vo);
        for (String s:list) {
            vo.setPicture(s);
            vo.setNo(boardDAO.selBoardNo(vo));
            boardDAO.insertPicture(vo);
        }
        return MyUtils.REDIRECTPAGE("/ojm");
    }

}