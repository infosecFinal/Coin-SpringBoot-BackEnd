package com.rest.api.DAO;

import com.rest.api.VO.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface BoardDAO {
    List<BoardVO> selectList(@Param("pageType") String pageType);
    List<BoardVO> selectListByContent(@Param("content") String content, @Param("pageType") String pageType);
    List<BoardVO> selectListByTitle(@Param("title") String title, @Param("pageType") String pageType);
    List<BoardVO> selectListByUser(@Param("user_id") String user_id, @Param("pageType") String pageType);
    int getNewestId();
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
    BoardVO selectListById(@Param("board_id") int id);

}
