package com.rest.api.Service.Impl;

import com.rest.api.DAO.AccountDAO;
import com.rest.api.Service.AccountService;
import com.rest.api.VO.AddressVO;
import com.rest.api.VO.LoginVO;
import com.rest.api.VO.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountDAO accountDAO;

    @Override
    public int insertUser(UserVO userVO) {

        return accountDAO.setUser(userVO);
    }

    public String getUserIDList(String id) {

        return accountDAO.getUserIDList(id);
    }

    @Override
    public UserVO checkUser(LoginVO loginVO) {

        return accountDAO.getUser(loginVO);
    }

    @Override
    public int deleteUser(LoginVO loginVO) {

        return accountDAO.deleteUser(loginVO);
    }

    @Override
    public int updateUser(UserVO userVO) {

        return accountDAO.updateUser(userVO);
    }

    @Override
    public UserVO getUserInfo(String id) {

        return accountDAO.getUserInfo(id);
    }

    @Override
    public List<AddressVO> findAddressList(String user_dong) {
        return  accountDAO.findAddressList(user_dong);
    }
}
