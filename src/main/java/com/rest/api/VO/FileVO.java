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
    private int file_id;
    private String fileName;
    private String contentType;
    private String filePath;
    private String origin_fileName;
}
