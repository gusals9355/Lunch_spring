package com.koreait.lunchproject1.model.dao;

import com.koreait.lunchproject1.model.vo.RepleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repleDAO")
public interface RepleDAO {

    List<RepleVO> getReples(RepleVO vo);

}
