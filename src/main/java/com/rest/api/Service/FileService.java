package com.rest.api.Service;

import com.rest.api.VO.FileVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileVO> selectFileList(int board_idx);
    FileVO selectFileById(int id);
    int uploadFile(MultipartFile[] files, int board_idx) throws IOException;
    int deleteFile(int file_id);
}
