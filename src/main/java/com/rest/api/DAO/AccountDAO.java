package com.rest.api.DAO;

import com.rest.api.VO.FindPw;
import com.rest.api.VO.LoginVO;
import com.rest.api.VO.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AccountDAO {
    int setUser(UserVO userVO);
    String getUserIDList(String id);
    UserVO getUser(LoginVO loginVO);
    int deleteUser(LoginVO loginVO);
    int updateUser(UserVO userVO);
    UserVO getUserInfo(@Param("login_id")String id);
    void updatePw(FindPw user) throws Exception;
}