package com.rest.api.Service.Impl;

import com.rest.api.DAO.BoardDAO;
import com.rest.api.Service.BoardService;
import com.rest.api.VO.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    @Override
    public List<BoardVO> selectBoardList() {
        return boardDAO.selectList();
    }
    @Override
    public int writeBoard(BoardVO boardVO) {
        return boardDAO.writeBoard(boardVO);
    }
    @Override
    public int updateBoard(BoardVO boardVO) {
        return boardDAO.updateBoard(boardVO);
    }
    @Override
    public int deleteBoard(int id) { return boardDAO.deleteBoard(id); }
}
