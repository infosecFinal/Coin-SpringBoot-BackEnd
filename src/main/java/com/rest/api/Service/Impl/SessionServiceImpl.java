package com.rest.api.Service.Impl;

import com.rest.api.DAO.SessionDAO;
import com.rest.api.Service.SessionService;
import com.rest.api.VO.SessionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionDAO sessionDAO;
    @Override
    public int insertSession(String sess_id, String user_id) {
        return sessionDAO.insertSession(sess_id, user_id);
    }

    @Override
    public SessionVO getSession(String sess_id) {
        return sessionDAO.getSession(sess_id);
    }
}
