package com.rest.api.Service.Impl;

import com.rest.api.DAO.BoardDAO;
import com.rest.api.DAO.FileDAO;
import com.rest.api.Service.BoardService;
import com.rest.api.VO.BoardVO;
import com.rest.api.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    private final FileDAO fileDAO;
    private final FileUtil fileUtil;

    @Override
    public List<BoardVO> selectBoardList(String pageType) {
        return boardDAO.selectList(pageType);
    }

    @Override
    public List<BoardVO> selectListByContent(String content, String pageType) {
        return boardDAO.selectListByContent(content, pageType);
    }

    @Override
    public List<BoardVO> selectListByTitle(String title, String pageType) {
        return boardDAO.selectListByTitle(title, pageType);
    }

    @Override
    public List<BoardVO> selectListByUser(String user_id, String pageType) {
        return boardDAO.selectListByUser(user_id, pageType);
    }

    @Override
    public int writeBoard(BoardVO boardVO) { return boardDAO.writeBoard(boardVO);}


    @Override
    public int updateBoard(BoardVO boardVO) {
        return boardDAO.updateBoard(boardVO);
    }
    @Override
    public int deleteBoard(BoardVO boardVO) { return boardDAO.deleteBoard(boardVO); }
    @Override
    public int getNewestId() {return boardDAO.getNewestId(); };
    @Override
    public BoardVO selectBoardListById(int id) { return boardDAO.selectListById(id); }

}
