package com.rest.api.DAO;

import com.rest.api.VO.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BoardDAO {
    List<BoardVO> selectList();
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(int id);
}
