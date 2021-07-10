package com.rest.api.Service;

import com.rest.api.VO.BoardVO;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BoardService {
    List<BoardVO> selectBoardList();
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);

}
