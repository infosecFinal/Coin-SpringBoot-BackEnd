package com.rest.api.util;

import com.rest.api.VO.FileVO;
import com.rest.api.exception.AttachFileException;
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
    private final String uploadPath = Paths.get("C:", "Temp", "Upload").toString();
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public List<FileVO> uploadFiles(MultipartFile[] files, int board_idx) throws IllegalStateException, IOException {
        if(files[0].getSize() < 1) return Collections.emptyList();

        List<FileVO> fliest = new ArrayList<>();

        File dir = new File(uploadPath);

        for(MultipartFile file: files) {
            try {
                String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                System.out.println(extension);
                String saveName = getRandomString() + "." + extension;

                File target = new File(uploadPath, saveName);
                file.transferTo(target);
                System.out.println(target.toString());
                FileVO fileVO = new FileVO();
                fileVO.setOrigin_file_Name(file.getOriginalFilename());
                fileVO.setFile_Name(saveName);
                fileVO.setFile_Path(uploadPath);
                fileVO.setBoard_id(board_idx);
                fileVO.setContent_type(file.getContentType());
                fliest.add(fileVO);
            } catch (IOException e) {
                throw new AttachFileException("["+file.getOriginalFilename()+"] failed to save file...");
            } catch (Exception e) {
                throw new AttachFileException("["+file.getOriginalFilename()+"] failed to save file ...");
            }
        }
        return fliest;
    }

}
