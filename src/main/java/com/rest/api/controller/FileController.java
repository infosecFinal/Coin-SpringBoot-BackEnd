package com.rest.api.controller;

import com.rest.api.DAO.FileDAO;
import com.rest.api.Service.BoardService;
import com.rest.api.Service.FileService;
import com.rest.api.Service.ResponseService;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags={"3. File Control"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/file")
public class FileController {

    private final BoardService boardService;
    private final FileService fileService;
    private final ResponseService responseService;

    @ApiOperation(value="파일 업로드", notes="파일을 업로드한다")
    @PostMapping(value="/upload")
    public SingleResult<Integer> uploadFile(@ApiParam(value="파일 업로드") @RequestBody MultipartFile[] files, HttpServletRequest req) throws IOException {
        String board_id = req.getParameter("board_id");
        int idx = 0;
        if(board_id.equals("new")) idx = boardService.selectBoardList().get(0).getId()+1;
        else idx = Integer.parseInt(board_id);
        return responseService.getSingleResult(fileService.uploadFile(files, idx));
    }
}
