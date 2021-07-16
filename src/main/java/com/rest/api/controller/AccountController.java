package com.rest.api.controller;

import com.rest.api.Service.AccountService;
import com.rest.api.Service.ResponseService;
import com.rest.api.Service.Impl.StorageServiceImpl;
import com.rest.api.VO.Login;
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

@Api(tags = {"1. Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/account")
public class AccountController {

    private final AccountService accountService;
    private final ResponseService responseService;
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

    @ApiOperation(value="계정 로그인", notes="가입한 계정으로 로그인을 한다")
    @PostMapping(value="/login")
    public SingleResult<User> checkUser(@ApiParam(value="계정 로그인") @RequestBody Login login)  {
        return responseService.getSingleResult(accountService.checkUser(login));
    }

    @ApiOperation(value="계정 삭제", notes="회원의 계정을 삭제한다")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteUser(@ApiParam(value="계정 삭제") @RequestBody Login login)  {
        return responseService.getSingleResult(accountService.deleteUser(login));
    }

    @ApiOperation(value="계정 정보출력", notes="회원의 계정 정보를 출력한다")
    @GetMapping(value="/print/{login_id}")
    public SingleResult<User> getUserInfo(@ApiParam(value="계정 정보출력") @PathVariable String login_id)  {
        return responseService.getSingleResult(accountService.getUserInfo(login_id));
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
