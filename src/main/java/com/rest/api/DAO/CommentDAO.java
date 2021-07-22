package com.rest.api.DAO;

import com.rest.api.VO.CommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDAO {
    int insertComment(CommentVO comment);
    int deleteComment(CommentVO comment);
    List<CommentVO> selectCommentByBoard(@Param("board_id") int board_id);
}
