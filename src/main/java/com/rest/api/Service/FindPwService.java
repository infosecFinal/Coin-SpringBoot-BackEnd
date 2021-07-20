package com.rest.api.Service;

import com.rest.api.VO.FindPw;
import javax.servlet.http.HttpServletResponse;

public interface FindPwService {
    void sendEmail(FindPw findPw, String div) throws Exception;
    void findPw(HttpServletResponse resp, FindPw findPw) throws Exception;
}