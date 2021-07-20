package com.rest.api.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

    private int idx;
    private String user_id;
    private int board_id;
    private String page_type;
    private String file_Name;
    private String origin_file_Name;
    private String file_Path;
    private char available;
    private String content_type;

    @Override
    public String toString() {
        return "FileVO{" +
                "idx=" + idx +
                ", user_id='" + user_id + '\'' +
                ", board_id=" + board_id +
                ", page_type='" + page_type + '\'' +
                ", file_Name='" + file_Name + '\'' +
                ", origin_file_Name='" + origin_file_Name + '\'' +
                ", file_Path='" + file_Path + '\'' +
                ", available=" + available +
                ", content_type='" + content_type + '\'' +
                '}';
    }
}
