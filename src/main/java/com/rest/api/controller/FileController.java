package com.rest.api.controller;

import com.rest.api.Service.BoardService;
import com.rest.api.Service.FileService;
import com.rest.api.Service.ResponseService;
import com.rest.api.VO.FileVO;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;


import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

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
        String user_id = req.getParameter("user_id");
        String board_id = req.getParameter("board_id");
        String pageType = req.getParameter("pageType");
        System.out.println("ptype: "+pageType);
        int idx = 0;
        if(board_id.equals("new")) idx = boardService.getNewestId();
        else idx = Integer.parseInt(board_id);

        int res = fileService.uploadFile(files, idx, user_id, pageType);

        if (res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="프로필 이미지 검색", notes="해당 유저의 프로필 이미지 접근")
    @GetMapping(value="/profile/{user}")
    public void download(HttpServletResponse resp, @ApiParam("유저 id")@PathVariable String user) {
        System.out.println("download");
        FileVO fileVO = fileService.selectProfile(user);
        String uploadPath = fileVO.getFile_Path();
        String fileName = fileVO.getOrigin_file_Name();
        File file = new File(uploadPath, fileVO.getFile_Name());

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            resp.getOutputStream().write(data);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 실패");
        } catch (Exception e) {
            throw new RuntimeException("시스템 에러");
        }
    }

    @ApiOperation(value="파일 삭제", notes="파일 접근을 불가능하도록 한다")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteFile(@ApiParam(value="파일 삭제") @RequestBody FileVO fileVO) {
        int res = fileService.deleteFile(fileVO.getIdx());
        if(res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="파일 검색", notes="게시글 번호로 파일을 검색한다")
    @GetMapping(value="/list/{board_id}")
    public ListResult<FileVO> findFileByBoard_id(@ApiParam("게시글 번호") @PathVariable int board_id) {
        List<FileVO> files = fileService.selectFileList(board_id);
        if(files == null) throw new RuntimeException();
        return responseService.getListResult(files);
    }

    @ApiOperation(value="파일 다운로드", notes="파일 경로 및 이름으로 다운로드")
    @GetMapping(value="/download")
    @CrossOrigin(value={"*"}, exposedHeaders = {"Content-Disposition"})
    public void download(HttpServletRequest req, HttpServletResponse resp) {
        String uploadPath = req.getParameter("filePath");
        String fileName = req.getParameter("fileName");
        System.out.println(uploadPath+" "+fileName);
        File file = new File(uploadPath, fileName);

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            resp.getOutputStream().write(data);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 실패");
        } catch (Exception e) {
            throw new RuntimeException("시스템 에러");
        }

    }
    @ApiOperation(value="파일 다운로드", notes="파일 번호로 다운로드")
    @GetMapping(value="/download/safe/{id}")
    @CrossOrigin(value={"*"}, exposedHeaders = {"Content-Disposition"})
    public void download(HttpServletResponse resp, @ApiParam("파일 id")@PathVariable int id) {
        FileVO fileVO = fileService.selectFileById(id);

        String uploadPath = fileVO.getFile_Path();
        String fileName = fileVO.getOrigin_file_Name();
        File file = new File(uploadPath, fileVO.getFile_Name());

        try {
            byte[] data = FileUtils.readFileToByteArray(file);
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            resp.getOutputStream().write(data);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 실패");
        } catch (Exception e) {
            throw new RuntimeException("시스템 에러");
        }

    }
}
