package com.rest.api.controller;

import com.rest.api.Service.AccountService;
import com.rest.api.Service.ResponseService;
import com.rest.api.Service.SessionService;
import com.rest.api.Service.Impl.StorageServiceImpl;
import com.rest.api.VO.Login;
import com.rest.api.VO.SessionVO;
import com.rest.api.VO.User;
import com.rest.api.exception.StorageException;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Api(tags = {"1. Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/account")
public class AccountController {

    private final AccountService accountService;
    private final ResponseService responseService;
    private final SessionService sessionService;

    public String convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    @Autowired
    private final StorageServiceImpl storageServiceImpl;

    @ApiOperation(value="계정 생성", notes="회원 가입을 한다")
    @PostMapping(value="/register")
    public SingleResult<Integer> insertUser(@ApiParam(value="계정 생성") @RequestBody User user)  {
        return responseService.getSingleResult(accountService.insertUser(user));
    }

    @ApiOperation(value="계정 중복확인", notes="아이디 중복여부를 확인한다.")
    @PostMapping(value="/validation")
    public SingleResult<String> getUserIDList(@ApiParam(value="계정 중복확인") @RequestBody User dupuser)  {
        return responseService.getSingleResult(accountService.getUserIDList(dupuser.getUser_id()));
    }

    @ApiOperation(value="계정 로그인", notes="인증 토큰 발급")
    @PostMapping(value="/login")
    public SingleResult<String> checkUser(@ApiParam(value="계정 로그인") @RequestBody Login login, HttpServletResponse response, @CookieValue(value="access_token", required=false) Cookie access_token)  {
        User user = accountService.checkUser(login);
        if(user == null) {
            return responseService.getSingleResult("fail");
        }
        String token = "";

        token = convertStringToHex(user.getUser_id());
        if(sessionService.getSession(token) == null)
            sessionService.insertSession(token, user.getUser_id());

        return responseService.getSingleResult(token);
    }

    @ApiOperation(value="계정 삭제", notes="회원의 계정을 삭제한다")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteUser(@ApiParam(value="계정 삭제") @RequestBody Login login, @CookieValue(value="access_token", required=false) Cookie access_token)  {
        User user = accountService.checkUser(login);
        SessionVO sess = sessionService.getSession(access_token.getValue());
        if(user==null || sess == null || !user.getUser_id().equals(sess.getUser_id())) {
            return responseService.getSingleResult(0);
        }
        return responseService.getSingleResult(accountService.deleteUser(login));
    }

    @ApiOperation(value="계정 정보출력", notes="회원의 계정 정보를 출력한다")
    @GetMapping(value="/print")
    public SingleResult<User> getUserInfo(@CookieValue(value="access_token", required=false) Cookie access_token)  {
        System.out.println("abcd");
        if(access_token == null) return responseService.getSingleResult(new User());
        String cookie = access_token.getValue();
        System.out.println(cookie);
        SessionVO sess = sessionService.getSession(cookie);
        if(sess == null) {
            return responseService.getSingleResult(new User());
        }
        System.out.println("Qwer");
        return responseService.getSingleResult(accountService.getUserInfo(sess.getUser_id()));
    }

    @ApiOperation(value="유효성 검증", notes="토큰의 유효성 검증")
    @GetMapping(value="/valid")
    public SingleResult<String> validation(@CookieValue(value="access_token", required=true) Cookie access_token) {
        String cookie = access_token.getValue();
        SessionVO sess = sessionService.getSession(cookie);
        String res = "";
        if(sess != null) res = "valid";
        else res = "not valid";
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="계정 정보수정", notes="회원의 계정 정보를 수정한다")
    @PostMapping(value="/mypage/update")
    public SingleResult<Integer> updateUser(@ApiParam(value="계정 정보수정") @RequestBody User user){
        return responseService.getSingleResult(accountService.updateUser(user));
    }

    @ApiOperation(value="계정 이미지 수정", notes="회원의 계정 이미지를 수정한다")
    @RequestMapping(value = "/mypage/update/upload", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public SingleResult<String> uploadImage(@ApiParam(value="계정 이미지 수정") @RequestBody MultipartFile file) throws StorageException {
        storageServiceImpl.uploadImage(file);
        return responseService.getSingleResult(storageServiceImpl.uploadImage(file));
    }

}
