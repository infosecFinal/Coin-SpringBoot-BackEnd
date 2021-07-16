package com.rest.api.DAO;

import com.rest.api.VO.SessionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SessionDAO {
    int insertSession(@Param(value="sess_id") String sess_id, @Param(value="user_id") String user_id);
    SessionVO getSession(@Param(value="sess_id") String sess_id);
}
