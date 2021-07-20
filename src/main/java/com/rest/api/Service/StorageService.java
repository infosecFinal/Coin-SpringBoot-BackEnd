package com.rest.api.Service;

import com.rest.api.VO.ProfileImg;
import com.rest.api.exception.StorageException;

public interface StorageService {
    String uploadImage(ProfileImg profileImg) throws StorageException;
}