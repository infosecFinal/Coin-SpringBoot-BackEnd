package com.rest.api.controller;


import com.rest.api.DAO.ChartDAO;
import com.rest.api.Service.ResponseService;
import com.rest.api.VO.ChartVO;
import com.rest.api.model.response.ListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/chart")
public class ChartController {

    private final ResponseService responseService;
    private final ChartDAO chartDAO;

    @GetMapping(value="/btc")
    public ListResult<ChartVO> getBtc() {
        return responseService.getListResult(chartDAO.getChartList());
    }
}
