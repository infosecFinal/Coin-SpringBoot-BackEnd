package com.rest.api.advice;

import com.rest.api.Service.ResponseService;
import com.rest.api.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult();
    }
}
