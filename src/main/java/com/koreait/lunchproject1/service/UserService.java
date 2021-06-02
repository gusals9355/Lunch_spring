package com.koreait.lunchproject1.service;

import com.koreait.lunchproject1.model.vo.MemberVO;

import javax.servlet.http.HttpSession;

public interface UserService {
    boolean joinP(MemberVO vo);

    boolean editNickNameP()

}
