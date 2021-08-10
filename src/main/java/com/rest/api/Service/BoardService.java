package com.rest.api.Service;

import com.rest.api.VO.BoardVO;
import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoardList(String pageType);
    List<BoardVO> selectListByContent(String content, String pageType);
    List<BoardVO> selectListByTitle(String title, String pageType);
    List<BoardVO> selectListByUser(String user_id, String pageType);
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
    int getNewestId();
    BoardVO selectBoardListById(int id);
}