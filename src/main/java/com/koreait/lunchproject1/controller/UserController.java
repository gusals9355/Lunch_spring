package com.koreait.lunchproject1.controller;

import com.google.gson.Gson;
import com.koreait.lunchproject1.model.vo.MemberVO;
import com.koreait.lunchproject1.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login.go")
    public String login(Model model, HttpSession session){
        return userService.login(model,session);
    }
    @PostMapping("/login.go")
    public String loginP(Model model, MemberVO vo, HttpSession session){
        return userService.loginP(vo,session,model);
    }
    @GetMapping("/logout.do")
    public String logout(HttpSession session){
        return userService.logout(session);
    }
    @GetMapping("/join.go")
    public String join(Model model, HttpSession session){
        return userService.join(model,session);
    }
    @ResponseBody
    @RequestMapping(value = "/join.go", method = RequestMethod.POST)
    public String joinP(@RequestBody MemberVO vo, Model model, HttpSession session) {
        Gson gson = new Gson();
        String result = gson.toJson(userService.joinP(vo));
        return result;
    }

    @GetMapping("/edit/nickname.do")
    public String editNickName(Model model, HttpSession session){
        return userService.editNickName(model, session);
    }
    @PostMapping("/edit/nickname.do")
    public String editNickNameP(Model model, HttpSession session, MemberVO vo){
        return userService.editNickNameP(vo,session, model);
    }

    @GetMapping("/ranking.do")
    public String ranking(Model model, HttpSession session, MemberVO vo){
        return userService.ranking(model, session, vo);
    }

    @GetMapping("/pw_check.do")
    public String pw_check(Model model, HttpSession session, MemberVO vo){
        return userService.pw_check(model, session, vo);
    }

    @PostMapping("/pw_check.do")
    public String pw_checkP(Model model, MemberVO vo, HttpSession session){
        return userService.pw_checkP(model, vo, session);
    }

    @RequestMapping(value = "/edit.do")
    public String myPage(Model model, HttpSession session, MemberVO vo){
        return userService.myPage(model, session, vo);
    }

    @GetMapping("/edit/pw.go")
    public String editPw(Model model, HttpSession session, MemberVO vo){
        return userService.editPw(model, session, vo);
    }

    @PostMapping("/edit/pw.go")
    public String editPwP(Model model, HttpSession session, MemberVO vo){
        return userService.editPwP(model, session, vo);
    }

    @GetMapping("/remove_user.do")
    public String removeUser(Model model, HttpSession session, MemberVO vo){
        return userService.removeUser(model, session, vo);
    }

    @GetMapping("findId.go")
    public String findId(Model model, HttpSession session, MemberVO vo){
        return userService.findId(model, session, vo);
    }
    @PostMapping("findId.go")
    public String findIdP(Model model, HttpSession session, MemberVO vo){
        return userService.findIdP(model, session, vo);
    }

    @GetMapping("findPw.go")
    public String findPw(Model model, HttpSession session, MemberVO vo){
        return userService.findPw(model, session, vo);
    }

    @PostMapping("findPw.go")
    public String findPwP(Model model, HttpSession session, MemberVO vo){
        return userService.findPwP(model, session, vo);
    }

}
