package com.rest.api.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oracle.sql.DATE;
import oracle.sql.TIMESTAMP;

import java.sql.Clob;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ChartVO {
    private float high;
    private float low;
    private float open;
    private float close;
    private String datetime;
}