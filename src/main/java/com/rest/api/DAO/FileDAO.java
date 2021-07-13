package com.rest.api.DAO;

import com.rest.api.VO.FileVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileDAO {
    int insertFile(@Param("list") List<FileVO> fileVO,@Param("board_idx") int board_idx);
    List<FileVO> selectFile(@Param("board_idx") int board_idx);
    int deleteFile(@Param("board_idx") int board_idx);
}
