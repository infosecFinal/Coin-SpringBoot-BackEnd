package com.rest.api.DAO;

import com.rest.api.VO.ChartVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ChartDAO {
    List<ChartVO> getChartList();
}
