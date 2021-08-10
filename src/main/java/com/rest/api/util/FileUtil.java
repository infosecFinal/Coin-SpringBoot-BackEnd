package com.rest.api.util;

import com.rest.api.VO.FileVO;
import com.rest.api.VO.UserVO;
import com.rest.api.exception.AttachFileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {
//    private final String uploadPath = Paths.get("/usr", "local", "tomcat", "weak", "Coin-Weak",  "upload").toString();
//    private final String uploadPath = Paths.get("C:", "Temp", "Upload").toString();

    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public List<FileVO> uploadFiles(MultipartFile[] files, int board_id, String user_id, String pageType) throws IllegalStateException {
        System.out.println("len:" + files.length);
        System.out.println("size: " + files[0].getSize());
        if (files[0].getSize() < 1) return Collections.emptyList();

        List<FileVO> file_lst = new ArrayList<>();

        if(pageType.equals("notice")) {
            String uploadPath = Paths.get("C:", "Temp","Weak").toString();
            for (MultipartFile file : files) {
                try {
                    String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                    String originName = FilenameUtils.removeExtension(file.getOriginalFilename());
                    String saveName = originName + "." + extension;
                    int i = 1;
                    File target = new File(uploadPath, file.getOriginalFilename());
                    while (target.exists()) {
                        saveName = originName + Integer.toString(i) + "." + extension;
                        target = new File(uploadPath, saveName);
                        i++;
                    }
                    file.transferTo(target);
                    FileVO fileVO = new FileVO();
                    fileVO.setUser_id(user_id);
                    System.out.println(fileVO.toString());
                    if (board_id != 0) {
                        fileVO.setPage_type("NOTICE");
                    } else {
                        fileVO.setPage_type("MYPAGE");
                    }

                    fileVO.setOrigin_file_Name(file.getOriginalFilename());
                    fileVO.setFile_Name(saveName);
                    fileVO.setFile_Path(uploadPath);
                    fileVO.setBoard_id(board_id);
                    fileVO.setContent_type(file.getContentType());

                    file_lst.add(fileVO);
                } catch (IOException e) {
                    throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
                } catch (Exception e) {
                    throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file ...");
                }
            }
        } else {
            String uploadPath = Paths.get("C:", "Temp","Upload").toString();
            for (MultipartFile file : files) {
                try {
                    String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                    String saveName = getRandomString() + "." + extension;

                    File target = new File(uploadPath, saveName);
                    file.transferTo(target);
                    FileVO fileVO = new FileVO();
                    fileVO.setUser_id(user_id);
                    System.out.println(fileVO.toString());
                    if(board_id!=0) {
                        fileVO.setPage_type("BOARD");
                    } else {
                        fileVO.setPage_type("MYPAGE");
                    }

                    System.out.println(fileVO.getPage_type());
                    fileVO.setOrigin_file_Name(file.getOriginalFilename());
                    System.out.println(file.getOriginalFilename());
                    fileVO.setFile_Name(saveName);
                    fileVO.setFile_Path(uploadPath);
                    fileVO.setBoard_id(board_id);
                    fileVO.setContent_type(file.getContentType());
                    System.out.println(fileVO.toString());
                    file_lst.add(fileVO);
                } catch (IOException e) {
                    throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
                } catch (Exception e) {
                    throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file ...");
                }
            }
        }
        return file_lst;
    }
}