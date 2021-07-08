package com.rest.api.VO;
import org.springframework.stereotype.Repository;

@Repository("dataSource")
public class MyDataSource {
    public MyDataSource() {
        setDriverClassName("oracle.jdbc.driver.OracleDriver");
        setUrl("jdbc:oracle:thin:@localhost.com:11521:xe");
        setUsername("mybtc");
        setPassword("mybtc");
    }

    private void setPassword(String mybtc) {
    }

    private void setUsername(String mybtc) {
    }

    private void setUrl(String s) {
    }

    private void setDriverClassName(String s) {
    }


}
