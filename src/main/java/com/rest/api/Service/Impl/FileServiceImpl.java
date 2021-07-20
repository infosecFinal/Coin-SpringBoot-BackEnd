package com.rest.api.Service.Impl;

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

    private final FileDAO fileDAO;
    private final FileUtil fileUtil;
    @Override
    public List<FileVO> selectFileList(int board_id) {
        List<FileVO> lst = new ArrayList<>();
        lst = fileDAO.selectFile(board_id);
        return lst;
    }

    @Override
    public FileVO selectFileById(int id) {
        return fileDAO.selectFileById(id);
    }

    @Override
    public int uploadFile(MultipartFile[] files, int board_id, String user_id) throws IOException {
        int queryResult = 1;

        List<FileVO> fileList = fileUtil.uploadFiles(files, board_id, user_id);
        System.out.println("After File Upload...");
        System.out.println(CollectionUtils.isEmpty(fileList));
        if (!CollectionUtils.isEmpty(fileList)) {
            System.out.println("to inserted: "+ fileList.get(0).toString());
            queryResult = fileDAO.insertFile(fileList, board_id, user_id);
            System.out.println(queryResult);
            if(queryResult < 1) {
                queryResult = 0;
            }
        }
        return queryResult;
    }

    @Override
    public int deleteFile(int file_id) {
        return fileDAO.deleteFile(file_id);
    }

    @Override
    public FileVO selectProfile(String user_id) { return fileDAO.selectProfile(user_id);}
}
