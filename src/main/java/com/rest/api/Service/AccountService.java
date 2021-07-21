package com.rest.api.Service;

import com.rest.api.VO.AddressVO;
import com.rest.api.VO.LoginVO;
import com.rest.api.VO.UserVO;

import java.util.List;

public interface AccountService {

    int insertUser(UserVO userVO);
    String getUserIDList(String id);
    UserVO checkUser(LoginVO loginVO);
    int deleteUser(LoginVO loginVO);
    int updateUser(UserVO userVO);
    UserVO getUserInfo(String id);

    List<AddressVO> findAddressList(String user_dong);
}