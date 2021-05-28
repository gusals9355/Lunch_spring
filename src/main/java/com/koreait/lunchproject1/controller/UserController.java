package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/login.go")
    public String login(Model model, HttpSession session){
        if(MyUtils.getLoginUser(session) != null){
            return MyUtils.REDIRECTPAGE("/ojm");
        }
        MyUtils.setTemplate(model,"로그인 | 오점뭐?","login",session);
        return MyUtils.TEMPLATE;
    }



    @PostMapping("/login.go")
    public String loginP(Model model, MemberVO vo, HttpSession session){

        String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
        String hashedPw = memberDAO.getHashedPw(vo);
        if(hashedPw.equals("") || !BCrypt.checkpw(vo.getPw(), hashedPw)) { //비번 틀릴경우
            model.addAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
            return MyUtils.REDIRECTPAGE("login.go");
        }
        if(BCrypt.checkpw(vo.getPw(), hashedPw)) { // 로그인 성공
            //로그인 성공 시 포인트를 획득함
            //로그인 포인트는 하루에 한번만 받을 수 있음
            vo.setLog("로그인");
            vo.setLoginPoint(100);

            memberDAO.log(vo); //로그인시 로그들을 db에 저장
            try {
                memberDAO.logCheck(vo); //하루 최초 로그인 시 출석체크가 되는 메소드
                memberDAO.pointUp(vo);
            }catch (Exception e){
            }finally {
                session.setAttribute("userInfo", memberDAO.getUserInfo(vo));
                return MyUtils.REDIRECTPAGE("/ojm");
            }
        }
        return "";
    }

    @GetMapping("/logout.do")
    public String logout(Model model, HttpSession session){
        MemberVO vo = MyUtils.getLoginUser(session);
        vo.setLog("로그아웃");
        memberDAO.log(vo);
        MyUtils.logOutSession(session);

        return MyUtils.REDIRECTPAGE("/ojm");
    }

    @GetMapping("/join.go")
    public String join(Model model, HttpSession session){
        MyUtils.setTemplate(model,"회원가입 | 오늘 점심 뭐먹지?","join",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("/join.go")
    public String joinP(Model model, MemberVO vo){

        vo.setNickname(vo.getName());
        vo.setPw(BCrypt.hashpw(vo.getPw(),BCrypt.gensalt()));
        memberDAO.insertMember(vo);

        return MyUtils.REDIRECTPAGE("/ojm");
    }

    @GetMapping("/edit/nickname.do")
    public String editNickName(Model model, HttpSession session){

        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?","my/editNickName",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("/edit/nickname.do")
    public String editNickNameP(Model model, HttpSession session, MemberVO vo){
        vo.setId(MyUtils.getLoginUserID(session));
        memberDAO.editNick(vo);
        session.setAttribute("userInfo",memberDAO.getUserInfo(vo));
        return MyUtils.REDIRECTPAGE("/ojm");
    }

    @GetMapping("/ranking.do")
    public String ranking(Model model, HttpSession session, MemberVO vo){
        if(vo.getPage() == 0) vo.setPage(1);
        int pageCount = 10;
        int sIdx = (vo.getPage() -1) * pageCount;
        vo.setSIdx(sIdx);
        vo.setPageCount(pageCount);
        model.addAttribute("pageNum",sIdx);
        model.addAttribute("maxPage",memberDAO.getAllPage(vo));
        model.addAttribute("rankingList",memberDAO.getRanking(vo));

        MyUtils.setTemplate(model,"랭킹 | 오늘 점심 뭐먹지?","my/ranking",session);
        return MyUtils.TEMPLATE;
    }

    @GetMapping("/pw_check.do")
    public String pw_check(Model model, HttpSession session, MemberVO vo){

        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?","my/pwCheck",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("/pw_check.do")
    public String pw_checkP(Model model, MemberVO vo){
        if(BCrypt.checkpw(vo.getPw(), memberDAO.getHashedPw(vo))) {
            return MyUtils.REDIRECTPAGE("edit.do");
        }
        return MyUtils.REDIRECTPAGE("pw_check.do");
    }

    @RequestMapping(value = "/edit.do")
    public String myPage(Model model, HttpSession session, MemberVO vo){

        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?","my/mypage",session);
        return MyUtils.TEMPLATE;
    }

    @GetMapping("/edit/pw.do")
    public String editPw(Model model, HttpSession session, MemberVO vo){

        MyUtils.setTemplate(model,"내정보 | 오늘 점심 뭐먹지?","my/editPw",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("/edit/pw.do")
    public String editPwP(Model model, HttpSession session, MemberVO vo){
        vo.setId(MyUtils.getLoginUserID(session));
        vo.setPw(BCrypt.hashpw(vo.getPw(),BCrypt.gensalt()));
        vo.setLog("로그아웃");
        memberDAO.editPw(vo);
        memberDAO.log(vo);
        MyUtils.logOutSession(session);

        return MyUtils.REDIRECTPAGE("/user/login.go");
    }

    @GetMapping("/remove_user.do")
    public String removeUser(Model model, HttpSession session, MemberVO vo){
        vo.setId(MyUtils.getLoginUserID(session));
        memberDAO.removeUser(vo);
        MyUtils.logOutSession(session);
        return MyUtils.REDIRECTPAGE("/ojm");
    }

}
