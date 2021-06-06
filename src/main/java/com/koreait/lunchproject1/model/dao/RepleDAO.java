package com.koreait.lunchproject1.model.dao;

import com.koreait.lunchproject1.model.vo.RepleVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface RepleDAO {

    void insertReple(RepleVO vo);
    List<RepleVO> getReples(RepleVO vo);
    void delReple(RepleVO vo);
    void modReple(RepleVO vo);
}
