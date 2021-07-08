package com.rest.api.Service;

import com.rest.api.VO.Login;
import com.rest.api.VO.User;

public interface AccountService {

    void insertUser(User user);

    static String getUserIDList(String id) {
        return null;
    }

    User checkUser(Login login);

    void deleteUser(Login login);

    void updateUser(User user);
}
