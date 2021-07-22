package com.rest.api.Service.Impl;

import com.rest.api.DAO.CommentDAO;
import com.rest.api.Service.CommentService;
import com.rest.api.VO.CommentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    @Override
    public int insertComment(CommentVO comment) {
        return commentDAO.insertComment(comment);
    }

    @Override
    public int deleteComment(CommentVO comment) { return commentDAO.deleteComment(comment); }

    @Override
    public List<CommentVO> selectCommentByBoard(int board_id) {
        return commentDAO.selectCommentByBoard(board_id);
    }
}
