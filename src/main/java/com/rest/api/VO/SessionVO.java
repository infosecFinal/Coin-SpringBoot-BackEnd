package com.rest.api.VO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionVO {
    private String session_id;
    private String user_id;
}