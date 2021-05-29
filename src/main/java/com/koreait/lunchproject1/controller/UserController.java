package com.koreait.lunchproject1.controller;

import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        String hashedPw = "";
        try{
            hashedPw = memberDAO.getHashedPw(vo);
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
            if(hashedPw.equals("") || !BCrypt.checkpw(vo.getPw(), hashedPw)) { //비번 틀릴경우
                model.addAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
                MyUtils.setTemplate(model,"로그인 | 오점뭐?","login",session);
                return MyUtils.TEMPLATE;
            }
        }catch (Exception e){
            model.addAttribute("msg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
            MyUtils.setTemplate(model,"로그인 | 오점뭐?","login",session);
            return MyUtils.TEMPLATE;
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

    @ResponseBody
    @PostMapping("/join.go")
    public int joinP(MemberVO vo){
        vo.setNickname(vo.getName());
        vo.setPw(BCrypt.hashpw(vo.getPw(),BCrypt.gensalt()));
        try {
            memberDAO.insertMember(vo);
        }catch (Exception e){ //TODO: 바로해야함
            
        }
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
    public String pw_checkP(Model model, MemberVO vo, HttpSession session){
        if(BCrypt.checkpw(vo.getPw(), memberDAO.getHashedPw(vo))) {
            return MyUtils.REDIRECTPAGE("edit.do");
        }
        model.addAttribute("msg","비밀번호를 다시 확인해주세요.");
        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?","my/pwCheck",session);
        return MyUtils.TEMPLATE;
    }

    @RequestMapping(value = "/edit.do")
    public String myPage(Model model, HttpSession session, MemberVO vo){

        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?","my/mypage",session);
        return MyUtils.TEMPLATE;
    }

    @GetMapping("/edit/pw.go")
    public String editPw(Model model, HttpSession session, MemberVO vo){
        MyUtils.setTemplate(model,"내정보 | 오늘 점심 뭐먹지?","my/editPw",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("/edit/pw.go")
    public String editPwP(Model model, HttpSession session, MemberVO vo){
        if(MyUtils.getLoginUser(session) != null){
            vo.setId(MyUtils.getLoginUserID(session));
            vo.setLog("로그아웃");
            memberDAO.log(vo);
            MyUtils.logOutSession(session);
        }
        vo.setPw(BCrypt.hashpw(vo.getPw(),BCrypt.gensalt()));
        memberDAO.editPw(vo);

        return MyUtils.REDIRECTPAGE("/user/login.go");
    }

    @GetMapping("/remove_user.do")
    public String removeUser(Model model, HttpSession session, MemberVO vo){
        vo.setId(MyUtils.getLoginUserID(session));
        memberDAO.removeUser(vo);
        MyUtils.logOutSession(session);
        return MyUtils.REDIRECTPAGE("/ojm");
    }

    @GetMapping("findId.go")
    public String findId(Model model, HttpSession session, MemberVO vo){
        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?" ,"findId",session);
        return MyUtils.TEMPLATE;
    }
    @PostMapping("findId.go")
    public String findIdP(Model model, HttpSession session, MemberVO vo){
        List<String> idList = memberDAO.selectIdList(vo);
        model.addAttribute("idList",idList);
        if(idList.size() == 0){
            model.addAttribute("msg","일치하는 정보가 없습니다.");
        }
        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?" ,"findId",session);
        return MyUtils.TEMPLATE;
    }

    @GetMapping("findPw.go")
    public String findPw(Model model, HttpSession session, MemberVO vo){
        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?" ,"findPw",session);
        return MyUtils.TEMPLATE;
    }

    @PostMapping("findPw.go")
    public String findPwP(Model model, HttpSession session, MemberVO vo){
        if(memberDAO.findPw(vo) == true){
            return MyUtils.REDIRECTPAGE("/user/edit/pw.go?id="+vo.getId());
        }
        model.addAttribute("msg","일치하는 정보가 없습니다.");
        MyUtils.setTemplate(model,"오늘 점심 뭐먹지?" ,"findPw",session);
        return MyUtils.TEMPLATE;
    }

}
