package com.rest.api.controller;

import com.rest.api.Service.AccountService;
import com.rest.api.VO.Login;
import com.rest.api.VO.StatusRes;
import com.rest.api.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/api")
public class APIController {

    @Autowired
    AccountService accountService;
    StatusRes statusRes = new StatusRes();

    @PostMapping("/uregi")
    public StatusRes regUser(@RequestBody User regUser) {

        // validation
        String users = AccountService.getUserIDList(regUser.getUser_id());

        if (users == null) {

            accountService.insertUser(regUser);
            statusRes.setStatus(100); // 유저 회원가입 성공
        } else {

            statusRes.setStatus(101); // 유저 회원가입 실패
        }

        return statusRes;
    }

    @PostMapping("/uidlogin") //sql -> 값 가져와서 성공 실패 보는
    public StatusRes LoginUser(HttpServletRequest request, @RequestBody Login login) {

        User user = accountService.checkUser(login);
        HttpSession session = request.getSession();

        if (user == null) {

            statusRes.setStatus(103);
        } else {
            statusRes.setStatus(102);
        }
        return statusRes;
    }

}
