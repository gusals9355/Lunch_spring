package com.koreait.lunchproject1.service;

import com.koreait.lunchproject1.model.vo.MemberVO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface UserService {
    int joinP(MemberVO vo);

    String editNickNameP(MemberVO vo, HttpSession session, Model model);

    String login(Model model,HttpSession session);

    String loginP(MemberVO vo,HttpSession session, Model model);

    String logout(HttpSession session);

    String join(Model model, HttpSession session);
    String editNickName(Model model, HttpSession session);
    String ranking(Model model, HttpSession session, MemberVO vo);
    String pw_check(Model model, HttpSession session, MemberVO vo);
    String pw_checkP(Model model, MemberVO vo, HttpSession session);
    String myPage(Model model, HttpSession session, MemberVO vo);
    String editPw(Model model, HttpSession session, MemberVO vo);
    String editPwP(Model model, HttpSession session, MemberVO vo);
    String removeUser(Model model, HttpSession session, MemberVO vo);
    String findId(Model model, HttpSession session, MemberVO vo);
    String findIdP(Model model, HttpSession session, MemberVO vo);
    String findPw(Model model, HttpSession session, MemberVO vo);
    String findPwP(Model model, HttpSession session, MemberVO vo);
}
