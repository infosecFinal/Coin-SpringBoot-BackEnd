package com.rest.api.Service.Impl;

import com.rest.api.DAO.BoardDAO;
import com.rest.api.DAO.FileDAO;
import com.rest.api.Service.BoardService;
import com.rest.api.VO.BoardVO;
import com.rest.api.VO.FileVO;
import com.rest.api.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    private final FileDAO fileDAO;
    private final FileUtil fileUtil;

    @Override
    public List<BoardVO> selectBoardList() {
        return boardDAO.selectList();
    }

    @Override
    public List<BoardVO> selectListByContent(String content) {
        return boardDAO.selectListByContent(content);
    }

    @Override
    public List<BoardVO> selectListByTitle(String title) {
        return boardDAO.selectListByTitle(title);
    }

    @Override
    public List<BoardVO> selectListByUser(String user_id) {
        return boardDAO.selectListByUser(user_id);
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
    public BoardVO selectBoardListById(int id) { return boardDAO.selectListById(id); }

}
