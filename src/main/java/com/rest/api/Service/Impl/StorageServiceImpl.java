package com.rest.api.Service.Impl;


import com.rest.api.DAO.AccountDAO;
import com.rest.api.Service.StorageService;
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

    @Autowired
    private final AccountDAO accountDAO;

    @Value("${java.io.tmpdir}")
    private String path;

    public String uploadImage(MultipartFile file) throws StorageException {

        if (file.isEmpty()) {

            throw new StorageException("Failed to store empty file");
        }

        try {
            var fileName = file.getOriginalFilename();
            var is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

            var msg = String.format("Failed to store file %f", file.getName());

            throw new StorageException(msg, e);
        }
        return path+file.getOriginalFilename();
    }
}
