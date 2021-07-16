package com.rest.api.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_phone;
    private String user_address;
    private String user_email;
    private String user_gender;
    private MultipartFile user_image;

}
