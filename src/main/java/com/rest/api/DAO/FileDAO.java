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
    List<FileVO> selectFile(@Param("board_id") int board_idx);
    FileVO selectFileById(@Param("id") int id);
    int deleteFile(@Param("file_id") int file_id);
}
