package com.rest.api.Service;

import com.rest.api.exception.StorageException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String uploadImage(MultipartFile file) throws StorageException;
}
