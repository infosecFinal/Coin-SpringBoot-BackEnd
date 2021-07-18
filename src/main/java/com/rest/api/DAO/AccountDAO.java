package com.rest.api.DAO;

import com.rest.api.VO.FindPw;
import com.rest.api.VO.Login;
import com.rest.api.VO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountDAO {
    int setUser(User user);
    String getUserIDList(String id);
    User getUser(Login login);
    int deleteUser(Login login);
    int updateUser(User user);
    User getUserInfo(@Param("login_id")String id);
    void updatePw(FindPw user) throws Exception;
}
