package com.rest.api.controller;

import com.rest.api.Service.AccountService;
import com.rest.api.VO.Login;
import com.rest.api.VO.StatusRes;
import com.rest.api.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    AccountService accountService;
    StatusRes statusRes = new StatusRes();

    @PostMapping("/udup")
    public StatusRes dupUser(@RequestBody User dupUser) {

        // validation
        StatusRes statusRes = new StatusRes();
        String users = AccountService.getUserIDList(dupUser.getUser_id());

        if (users == null) {

            statusRes.setStatus(100);
        } else {

            statusRes.setStatus(101);
        }

        return statusRes;
    }

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

        StatusRes statusRes = new StatusRes();
        User user = accountService.checkUser(login);
        //HttpSession session = request.getSession();

        if (user == null) {

            statusRes.setStatus(103);
        } else {

            statusRes.setStatus(102);
        }
        return statusRes;
    }


}
