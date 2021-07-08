package com.rest.api.DAO;

import com.rest.api.VO.Login;
import com.rest.api.VO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountDAO {

    void setUser(User user);
    String getUserIDList(String id);
    User getUser(Login login);
    void deleteUser(Login login);
    void updateUser(User user);
}
