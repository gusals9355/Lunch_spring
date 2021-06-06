package com.koreait.lunchproject1.model.dao;

import com.koreait.lunchproject1.model.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("memberDAO")
public interface MemberDAO {
    int insertMember(MemberVO bean);

    int getAllPage(MemberVO vo);

    String getHashedPw(MemberVO vo);

    int getBoardCount(MemberVO vo);

    List<MemberVO> getRanking(MemberVO vo);

    MemberVO searchUser(MemberVO vo);

    MemberVO getUserInfo(MemberVO vo);

    List<String> selectIdList(MemberVO vo);

    boolean findPw(MemberVO vo);

    String regiManager(String code);

    //로그인 정보 (로그)를 저장하는 메소드
    void log(MemberVO vo);

    void logCheck(MemberVO vo);

    void pointUp(MemberVO vo);

    void removeUser(MemberVO vo);

    void editNick(MemberVO vo);

    void editPw(MemberVO vo);

    void upPoint(MemberVO vo);
    void downPoint(MemberVO vo);

    void modManager(String id);
}
