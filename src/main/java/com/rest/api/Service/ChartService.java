package com.rest.api.Service;

import com.rest.api.VO.ChartVO;

import java.sql.Clob;
import java.util.List;

public interface ChartService {
    List<ChartVO> getBtcList();
    List<ChartVO> getEthList();
    List<ChartVO> selectBtcListByDate(String datetime);
}