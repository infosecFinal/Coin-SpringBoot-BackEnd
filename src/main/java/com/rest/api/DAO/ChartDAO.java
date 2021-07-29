package com.rest.api.DAO;

import com.rest.api.VO.BoardVO;
import com.rest.api.VO.ChartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Clob;
import java.util.List;

@Component
@Mapper

public interface ChartDAO {
    List<ChartVO> getBtcList();
    List<ChartVO> getEthList();
    List<ChartVO> selectBtcListByDate(@Param("datetime") String datetime);
}