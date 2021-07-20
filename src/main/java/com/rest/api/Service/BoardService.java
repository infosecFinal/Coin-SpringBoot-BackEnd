package com.rest.api.Service;

import com.rest.api.VO.BoardVO;
import com.rest.api.VO.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoardList();
    List<BoardVO> selectListByContent(String content);
    List<BoardVO> selectListByTitle(String title);
    List<BoardVO> selectListByUser(String user_id);
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(BoardVO boardVO);
    BoardVO selectBoardListById(int id);
}
