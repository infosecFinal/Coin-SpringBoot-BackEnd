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
public class UserVO {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_phone;
    private String user_address;
    private String user_email;
    private String user_gender;
    private Boolean isAdmin;
    private int image_idx;

    @Override
    public String toString() {
        return "UserVO{" +
                "user_id='" + user_id + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", isAdmin=" + isAdmin +
                ", image_idx=" + image_idx +
                '}';
    }
}
