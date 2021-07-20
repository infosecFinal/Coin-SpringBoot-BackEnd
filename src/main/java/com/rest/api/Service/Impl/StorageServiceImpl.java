package com.rest.api.Service.Impl;


import com.rest.api.DAO.AccountDAO;
import com.rest.api.Service.AccountService;
import com.rest.api.Service.StorageService;
import com.rest.api.VO.ProfileImg;
import com.rest.api.exception.StorageException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor

public class StorageServiceImpl implements StorageService {

    @Value("${java.io.tmpdir}")
    private String path;
    private final AccountDAO accountDAO;

    public String uploadImage(ProfileImg profileImg) throws StorageException {
        if (profileImg.getProfile_image().isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        try {
            String id_ck = accountDAO.getUserIDList(profileImg.getProfile_id());
            if(id_ck.equals(profileImg.getProfile_id())) {
                var fileName = profileImg.getProfile_image().getOriginalFilename();
                var is = profileImg.getProfile_image().getInputStream();
                Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            var msg = String.format("Failed to store file %f", profileImg.getProfile_image().getName());
            throw new StorageException(msg, e);
        }
        accountDAO.uploadImage((ProfileImg) profileImg.getProfile_image());
        return path+profileImg.getProfile_image().getOriginalFilename();
    }
}
