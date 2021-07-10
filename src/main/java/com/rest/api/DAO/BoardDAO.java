package com.rest.api.DAO;

import com.rest.api.VO.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface BoardDAO {
    List<BoardVO> selectList();
    int writeBoard(BoardVO boardVO);
    int updateBoard(BoardVO boardVO);
}
