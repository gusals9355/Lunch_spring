package com.koreait.lunchproject1.model.dao;

import com.koreait.lunchproject1.model.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardDAO {
    int insertBoard(BoardVO vo);
    void insertPicture(BoardVO vo);

    void insertHeart(BoardVO vo);
    //	final String sql = "insert into favorite(no, id) values(?,?)";

    List<BoardVO> getAllBoard();

    BoardVO getBoard(BoardVO vo);
    List<String> getPicture(int no);

    void delBoard(BoardVO vo);

    void delPicture(BoardVO vo);

    void removeHeart(BoardVO vo);
    //TODO: 사진, map까지 수정
    void modBoard(BoardVO vo);

    int selBoardNo(BoardVO vo);

    void upReadCount(BoardVO vo);

}
