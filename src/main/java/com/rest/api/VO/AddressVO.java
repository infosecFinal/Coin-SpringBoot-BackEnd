package com.rest.api.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {
    private String do_sc_mc;
    private String si_gu;
    private String dong;
    private String ro;

    @Override
    public String toString() {
        return "AddressVO{" +
                "do_sc_mc='" + do_sc_mc + '\'' +
                ", si_gu='" + si_gu + '\'' +
                ", dong='" + dong + '\'' +
                ", ro='" + ro + '\'' +
                '}';
    }
}
