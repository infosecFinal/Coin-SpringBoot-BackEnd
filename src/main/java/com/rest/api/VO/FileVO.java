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
    private int board_id;
    private int id;
    private char fileavailable;
    private String file_Name;
    private String file_Path;
    private String origin_file_Name;
    private String content_type;
}
