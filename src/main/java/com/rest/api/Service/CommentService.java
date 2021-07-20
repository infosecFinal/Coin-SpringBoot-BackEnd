package com.rest.api.Service;

import com.rest.api.VO.CommentVO;

import java.util.List;

public interface CommentService {
    int insertComment(CommentVO comment);
    List<CommentVO> selectCommentByBoard(int board_id);
}
