package com.rest.api.Service.Impl;

import com.rest.api.DAO.AccountDAO;
import com.rest.api.Service.AccountService;
import com.rest.api.VO.Login;
import com.rest.api.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO dao;

    @Override
    public void insertUser(User user) {

        dao.setUser(user);
    }

    public String getUserIDList(String id) {

        return dao.getUserIDList(id);
    }

    @Override
    public User checkUser(Login login) {

        return dao.getUser(login);
    }

    @Override
    public void deleteUser(Login login) {

        dao.deleteUser(login);
    }

    @Override
    public void updateUser(User user) {

        dao.updateUser(user);
    }
}
