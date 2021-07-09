package com.rest.api.Service;

import com.rest.api.VO.Login;
import com.rest.api.VO.User;

public interface AccountService {

    void insertUser(User user);

    String getUserIDList(String id);

    User checkUser(Login login);

    void deleteUser(Login login);

    void updateUser(User user);
}
