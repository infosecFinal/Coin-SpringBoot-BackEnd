package com.rest.api.Service.Impl;

import com.rest.api.DAO.ChartDAO;
import com.rest.api.Service.ChartService;
import com.rest.api.VO.ChartVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Clob;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final ChartDAO chartDAO;

    @Override
    public List<ChartVO> getBtcList() {
        return chartDAO.getBtcList();
    }

    @Override
    public List<ChartVO> getEthList() {
        return chartDAO.getEthList();
    }

    @Override
    public List<ChartVO> selectBtcListByDate(String datetime) {
        return chartDAO.selectBtcListByDate(datetime);
    }
}