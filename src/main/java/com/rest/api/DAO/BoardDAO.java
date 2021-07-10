package com.rest.api.DAO;

import com.rest.api.VO.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface BoardDAO {
    List<BoardVO> selectList();
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
    BoardVO selectListById(@Param("board_id") int id);
}
