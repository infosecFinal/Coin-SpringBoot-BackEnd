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
    List<BoardVO> selectListByContent(@Param("content") String content);
    List<BoardVO> selectListByTitle(@Param("title") String title);
    List<BoardVO> selectListByUser(@Param("user_id") String user_id);
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
    BoardVO selectListById(@Param("board_id") int id);

}
