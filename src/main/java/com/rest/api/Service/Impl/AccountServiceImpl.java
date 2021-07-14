package com.rest.api.Service.Impl;

import com.rest.api.DAO.AccountDAO;
import com.rest.api.Service.AccountService;
import com.rest.api.VO.Login;
import com.rest.api.VO.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountDAO accountDAO;

    @Override
    public int insertUser(User user) {

        return accountDAO.setUser(user);
    }

    public String getUserIDList(String id) {

        return accountDAO.getUserIDList(id);
    }

    @Override
    public User checkUser(Login login) {

        return accountDAO.getUser(login);
    }

    @Override
    public int deleteUser(Login login) {

        return accountDAO.deleteUser(login);
    }

    @Override
    public int updateUser(User user) {

        return accountDAO.updateUser(user);
    }

    @Override
    public Login getUserInfo(String id) {

        return accountDAO.getUserInfo(id);
    }
}
