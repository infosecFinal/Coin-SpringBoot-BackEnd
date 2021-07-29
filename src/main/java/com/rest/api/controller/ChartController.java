package com.rest.api.controller;

import com.rest.api.Service.ChartService;
import com.rest.api.Service.ResponseService;
import com.rest.api.VO.BoardVO;
import com.rest.api.VO.ChartVO;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Clob;
import java.util.List;

@Api(tags = {"4. Chart"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/home")
public class ChartController {

    private final ChartService chartService;
    private final ResponseService responseService;

    @GetMapping(value = "/chart")
    public ListResult<ChartVO> getBtcList() {
        List<ChartVO> result = chartService.getBtcList();
        return responseService.getListResult(result);
    }

    @ApiOperation(value="BTC 날짜 조회", notes="String 타입 날짜로 조회한다")
    @GetMapping(value="/chart/{datetime}")
    public ListResult<ChartVO> selectBtcListByDate(@ApiParam(value="문자형 날짜") @PathVariable String datetime) {
        List<ChartVO> chart = chartService.selectBtcListByDate(datetime);
        if(chart == null) throw new RuntimeException();
        return responseService.getListResult(chart);
    }
    @GetMapping(value = "/chart_eth")
    public ListResult<ChartVO> getEthList() {
        List<ChartVO> result = chartService.getEthList();
        return responseService.getListResult(result);
    }
}