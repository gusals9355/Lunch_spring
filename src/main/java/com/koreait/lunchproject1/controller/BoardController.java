package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.BoardDAO;
import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.dao.RepleDAO;
import com.koreait.lunchproject1.model.vo.BoardVO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import com.koreait.lunchproject1.model.vo.RepleVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    HttpSession session;
    final String[] typelist = {"한식","양식","일식","중식","분식","카페","기타"};
    
    @GetMapping("/write.do")
    public String boardWrite(Model model){

        model.addAttribute("typelist",typelist);
        MyUtils.setTemplate(model,"글 등록 | 오늘 점심 뭐먹지?","board/write",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("/write.do")
    public String boardWrite(MultipartFile[] file, Model model, BoardVO vo, HttpServletRequest request) throws IOException {
        List<BoardVO> list1 =boardDAO.getAllBoard();
        for (BoardVO v:list1) {
            if(vo.getStore()== null||vo.getCategory().equals("") ||vo.getStar() == 0 || vo.getMapX() == 0 || vo.getMapY() == 0 || v.getStore().equals(vo.getStore())){
                if(v.getStore().equals(vo.getStore())){
                    model.addAttribute("msg","이미 리뷰된 가게입니다");
                }else{
                    model.addAttribute("msg","카테고리 혹은 평점, 매장을 선택해주세요.");
                }
                model.addAttribute("typelist",typelist);
                MyUtils.setTemplate(model,"글 등록 | 오늘 점심 뭐먹지?","board/write",session);
                return MyUtils.TEMPLATE;
            }
        }
        String uploadPath = request.getSession().getServletContext().getRealPath("/upload/boardImg/");
        UUID uuid = UUID.randomUUID();

        List<String> list = new ArrayList<>();
        for (MultipartFile m:file) {
            list.add(uuid+"_"+m.getOriginalFilename());
        }
        String boardStore =vo.getStore();
        String targetFolder = uploadPath+boardStore;
        new File(targetFolder).mkdirs();
        for(int i=0; i< file.length; i++){
            File target = new File(uploadPath+boardStore, list.get(i));

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

    @GetMapping("/views.do")
    public String views(Model model, BoardVO boardVO, RepleVO repleVO, @RequestParam(value = "no") int no,
                        @CookieValue(name="view")String cookie, HttpServletResponse response){
        repleVO.setNo(no);
        repleVO.setBoardno(boardVO.getNo());
        boardVO.setId(MyUtils.getLoginUserID(session));
        System.out.println(cookie);
        if(!(cookie.contains(String.valueOf(boardVO.getId())+"."+no))){
            cookie+= boardVO.getId()+"."+no+"/";
            boardDAO.upReadCount(boardVO);
        }
        boardVO = boardDAO.getBoard(boardVO);
        response.addCookie(new Cookie("view", cookie));
        model.addAttribute("picture",boardDAO.getPicture(no));
        model.addAttribute("boards",boardVO);
        model.addAttribute("reples", repleDAO.getReples(repleVO));
        MyUtils.setTemplate(model,boardVO.getTitle()+" | 오늘 점심 뭐먹지?","board/views",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("/views.do")
    public String viewsP(Model model, RepleVO repleVO){
        MemberVO memberVO = MyUtils.getLoginUser(session);
        repleVO.setId(memberVO.getId());
        repleVO.setNickname(memberVO.getNickname());
        repleDAO.insertReple(repleVO);
        return MyUtils.REDIRECTPAGE("views.do?no="+repleVO.getBoardno());
    }

    @GetMapping("/delBoard.do")
    public String delBoard(Model model, BoardVO boardVO, HttpServletRequest request, @RequestParam(value = "no") int no) throws IOException {
        String uploadPath = request.getSession().getServletContext().getRealPath("/upload/boardImg/");
        boardVO.setNo(no);
        boardVO.setId(MyUtils.getLoginUserID(session));
        String store = boardDAO.getBoard(boardVO).getStore();
        File folder = new File(uploadPath+ store);
        FileUtils.deleteDirectory(folder); //폴더의 하위 파일들까지 모두 연쇄삭제
        boardDAO.delBoard(boardVO);
        MemberVO memberVO = new MemberVO();
        memberVO.setId(MyUtils.getLoginUserID(session));
        memberVO.setWritePoint(25);
        memberDAO.downPoint(memberVO);
        MyUtils.reUserInfo(session,memberDAO);

        return MyUtils.REDIRECTPAGE("/ojm");
    }

    @GetMapping("/delReple.do")
    public String delReple(Model model, RepleVO repleVO){
        repleVO.setId(MyUtils.getLoginUserID(session));
        repleDAO.delReple(repleVO);
        return MyUtils.REDIRECTPAGE("views.do?no="+repleVO.getBoardno());
    }

    @GetMapping("/modReple.do")
    public String modReple(Model model, RepleVO repleVO){
        BoardVO vo = new BoardVO();
        vo.setId(MyUtils.getLoginUserID(session));
        vo.setNo(repleVO.getBoardno());
        model.addAttribute("repleNo", repleVO.getNo());
        model.addAttribute("picture",boardDAO.getPicture(repleVO.getBoardno()));
        model.addAttribute("boards", boardDAO.getBoard(vo));
        model.addAttribute("reples", repleDAO.getReples(repleVO));

        MyUtils.setTemplate(model, "오늘 점심 뭐먹지?","board/reple/modReple",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("/modReple.do")
    public String modRepleP(Model model, RepleVO repleVO){
        repleVO.setId(MyUtils.getLoginUserID(session));
        repleDAO.modReple(repleVO);
        MyUtils.setTemplate(model, "오늘 점심 뭐먹지?","board/reple/modReple",session);
        return MyUtils.REDIRECTPAGE("views.do?no="+repleVO.getBoardno());
    }

    @GetMapping("/modBoard.do")
    public String modBoard(Model model, BoardVO boardVO){
        boardVO.setId(MyUtils.getLoginUserID(session));
        model.addAttribute("typelist",typelist);
        model.addAttribute("board",boardDAO.getBoard(boardVO));

        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?","board/modBoard",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("/modBoard.do")
    public String modBoardP(MultipartFile[] file, Model model, BoardVO vo, HttpServletRequest request) throws IOException{
        String uploadPath = request.getSession().getServletContext().getRealPath("/upload/boardImg/");
        UUID uuid = UUID.randomUUID();

        List<String> list = new ArrayList<>();
        for (MultipartFile m:file) {
            list.add(uuid+"_"+m.getOriginalFilename());
        }
        String boardStore =vo.getStore();
        String targetFolder = uploadPath+boardStore;
        FileUtils.deleteDirectory(new File(targetFolder));
        new File(targetFolder).mkdirs();
        for(int i=0; i< file.length; i++){
            File target = new File(targetFolder, list.get(i));

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