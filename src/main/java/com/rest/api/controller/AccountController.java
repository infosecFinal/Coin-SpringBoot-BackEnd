package com.rest.api.controller;

import com.rest.api.Service.AccountService;
import com.rest.api.Service.FindPwService;
import com.rest.api.Service.ResponseService;
import com.rest.api.Service.SessionService;
import com.rest.api.VO.*;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = {"1. Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/account")
public class AccountController {

    private final AccountService accountService;
    private final ResponseService responseService;
    private final SessionService sessionService;
    private final FindPwService findPwService;

    private final HttpSession session;



    @ApiOperation(value="계정 생성", notes="회원 가입을 한다")
    @PostMapping(value="/register")
    public SingleResult<Integer> insertUser(@ApiParam(value="계정 생성") @RequestBody UserVO userVO)  {
        int res = accountService.insertUser(userVO);
        if(res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="계정 중복확인", notes="아이디 중복여부를 확인한다.")
    @PostMapping(value="/validation")
    public SingleResult<String> getUserIDList(@ApiParam(value="계정 중복확인") @RequestBody UserVO dupuser)  {
        String id = accountService.getUserIDList(dupuser.getUser_id());
        System.out.println(id);
        if(id == null) throw new RuntimeException();
        return responseService.getSingleResult(id);
    }

    @ApiOperation(value="계정 로그인", notes="인증 토큰 발급")
    @PostMapping(value="/login")
    public SingleResult<UserVO> checkUser(@ApiParam(value="계정 로그인") @RequestBody LoginVO loginVO, HttpServletRequest req)  {

        UserVO userVO = accountService.checkUser(loginVO);
        if(userVO == null) {
            throw new RuntimeException();
        }
        System.out.println(session.getId());
        session.invalidate();
        HttpSession newSession = req.getSession();
        session.setAttribute("id", userVO.getUser_id());
        session.setAttribute("name", userVO.getUser_name());
        return responseService.getSingleResult(userVO);
    }

    @ApiOperation(value="계정 삭제", notes="회원의 계정을 삭제한다")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteUser(@ApiParam(value="계정 삭제") @RequestBody LoginVO loginVO)  {
            UserVO userVO = accountService.checkUser(loginVO);
            String UserID = (String)session.getAttribute("id");
            if (userVO == null || UserID == null || !userVO.getUser_id().equals(UserID)) {
                throw new RuntimeException();
            }
            int res = accountService.deleteUser(loginVO);
            System.out.println(res);
            if (res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="계정 정보출력", notes="회원의 계정 정보를 출력한다")
    @GetMapping(value="/print")
    public SingleResult<UserVO> getUserInfo() throws Exception {
        return responseService.getSingleResult(accountService.getUserInfo((String) session.getAttribute("id")));
    }

    @ApiOperation(value="유효성 검증", notes="토큰의 유효성 검증")
    @GetMapping(value="/valid")
    public CommonResult validation() {
        System.out.println((String) session.getAttribute("id"));
        if(session.getAttribute("id") != null)
            return responseService.getSuccessResult();
        else throw new RuntimeException();
    }

    @ApiOperation(value="로그아웃", notes="세션 만료")
    @GetMapping(value="/logout")
    public CommonResult logout(HttpSession session) {
        session.invalidate();
        return responseService.getSuccessResult();
    }

    @ApiOperation(value="계정 정보수정", notes="회원의 계정 정보를 수정한다")
    @PostMapping(value="/mypage/update")
    public SingleResult<Integer> updateUser(@ApiParam(value="계정 정보수정") @RequestBody UserVO userVO) {
        String userId = (String) session.getAttribute("id");
        int res = 0;
        if(userId.equals(userVO.getUser_id())) {
            res = accountService.updateUser(userVO);
            if(res < 1) throw new RuntimeException();
        }
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="계정 비밀번호 찾기", notes="비밀번호를 분실한 회원에게 이메일로 임시 비밀번호를 전달한다.")
    @PostMapping(value = "/findpw")
    public void findPw(@ApiParam(value="계정 비밀번호 찾기") HttpServletResponse response, @RequestBody FindPw findPw) throws Exception {

        findPwService.findPw(response, findPw);
    }

    @ApiOperation(value="회원 주소 검색", notes="동/읍으로 국내 주소를 검색한다.")
    @GetMapping(value = "/findaddress")
    public ListResult<AddressVO> findAddressList(HttpServletRequest req) {
        String user_dong = req.getParameter("user_dong");
        System.out.println(user_dong);
        List<AddressVO> address = accountService.findAddressList(user_dong);
        if(address == null) throw new RuntimeException();
        return responseService.getListResult(address);
    }
}