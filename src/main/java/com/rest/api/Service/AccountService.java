package com.rest.api.Service;

import com.rest.api.VO.LoginVO;
import com.rest.api.VO.UserVO;

public interface AccountService {

    int insertUser(UserVO userVO);
    String getUserIDList(String id);
    UserVO checkUser(LoginVO loginVO);
    int deleteUser(LoginVO loginVO);
    int updateUser(UserVO userVO);
    UserVO getUserInfo(String id);
}