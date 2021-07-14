package com.rest.api.Service;

import com.rest.api.VO.Login;
import com.rest.api.VO.User;

public interface AccountService {

    int insertUser(User user);
    String getUserIDList(String id);
    User checkUser(Login login);
    int deleteUser(Login login);
    int updateUser(User user);
    Login getUserInfo(String id);
}
