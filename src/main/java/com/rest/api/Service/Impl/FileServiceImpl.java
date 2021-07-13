package com.rest.api.Service.Impl;

import com.rest.api.DAO.BoardDAO;
import com.rest.api.DAO.FileDAO;
import com.rest.api.Service.FileService;
import com.rest.api.VO.FileVO;
import com.rest.api.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final BoardDAO boardDAO;
    private final FileDAO fileDAO;
    private final FileUtil fileUtil;
    @Override
    public List<FileVO> selectFileList(int board_idx) {
        List<FileVO> lst = new ArrayList<>();
        return lst;
    }

    @Override
    public int uploadFile(MultipartFile[] files, int board_idx) throws IOException {
        int queryResult = 1;

        List<FileVO> fileList = fileUtil.uploadFiles(files, board_idx);

        if (!CollectionUtils.isEmpty(fileList)) {
            queryResult = fileDAO.insertFile(fileList, board_idx);
            if(queryResult < 1) {
                queryResult = 0;
            }
        }

        return queryResult;
    }

    @Override
    public int deleteFile(int board_idx) {
        return 0;
    }
}
