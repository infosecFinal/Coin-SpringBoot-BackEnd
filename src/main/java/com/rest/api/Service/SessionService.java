package com.rest.api.Service;

import com.rest.api.VO.SessionVO;

public interface SessionService {
    int insertSession(String sess_id, String user_id);
    SessionVO getSession(String sess_id);
}
