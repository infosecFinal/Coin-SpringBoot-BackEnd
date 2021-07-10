package com.rest.api.DAO;

import com.rest.api.VO.Login;
import com.rest.api.VO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountDAO {

    int setUser(User user);
    String getUserIDList(String id);
    User getUser(Login login);
    int deleteUser(Login login);
    int updateUser(User user);
}
