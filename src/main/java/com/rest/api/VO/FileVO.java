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

    private int id;
    private String user_id;
    private int board_id;
    private String page_type;
    private String file_Name;
    private String origin_file_Name;
    private String file_Path;
    private char available;
    private String content_type;
}
