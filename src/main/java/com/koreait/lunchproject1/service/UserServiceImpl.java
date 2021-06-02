package com.koreait.lunchproject1.service;

import com.koreait.lunchproject1.model.dao.MemberDAO;
import com.koreait.lunchproject1.model.vo.MemberVO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    MemberDAO memberDAO;

    @Override
    public boolean joinP(MemberVO bean) {
        bean.setNickname(bean.getName());
        bean.setPw(BCrypt.hashpw(bean.getPw(),BCrypt.gensalt()));
        try {
            memberDAO.insertMember(bean);
        }catch (Exception e){
            return true;
        }
        return false;
    }

    @Override
    public boolean getUserInfo(MemberVO vo, HttpSession session) {

        return false;
    }


}
