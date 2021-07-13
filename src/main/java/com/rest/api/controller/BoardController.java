package com.rest.api.controller;

import com.rest.api.DAO.BoardDAO;
import com.rest.api.Service.BoardService;
import com.rest.api.Service.Impl.BoardServiceImpl;
import com.rest.api.Service.ResponseService;
import com.rest.api.VO.BoardVO;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Api(tags = {"2. Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/board")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;

    @ApiOperation(value="게시글 전체 조회", notes="모든 게시글을 조회한다")
    @GetMapping(value="/lists")
    public ListResult<BoardVO> selectList() {
        List<BoardVO> result = boardService.selectBoardList();
        System.out.println(result.get(0));
        return responseService.getListResult(result);
    }

    @ApiOperation(value="게시글 작성", notes="게시글을 작성한다")
    @PostMapping(value="/insert")
    public SingleResult<Integer> writeBoard(@ApiParam(value = "게시글 작성") @RequestBody BoardVO boardVO) {
        return responseService.getSingleResult(boardService.writeBoard(boardVO));
    }

    @ApiOperation(value="게시글 수정", notes="게시글을 수정한다")
    @PostMapping(value="/update")
    public SingleResult<Integer> updateBoard(@ApiParam(value="게시글 수정") @RequestBody BoardVO boardVO) {
        return responseService.getSingleResult(boardService.updateBoard(boardVO));
    }

    @ApiOperation(value="게시글 삭제", notes="게시글을 비활성화 한다")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteBoard(@ApiParam(value="게시글 삭제") @RequestBody BoardVO boardVO) {
        return responseService.getSingleResult(boardService.deleteBoard(boardVO));
    }

    @ApiOperation(value="게시글 조회", notes="게시글 번호로 조회한다.")
    @GetMapping(value="/list/{board_id}")
    public SingleResult<BoardVO> selectListById(@ApiParam(value="게시글 번호") @PathVariable int board_id) {
        return responseService.getSingleResult(boardService.selectBoardListById(board_id));
    }

    @ApiOperation(value="게시글 검색", notes="게시글을 타입별로 검색한다.")
    @GetMapping(value="/find")
    public ListResult<BoardVO> findList(HttpServletRequest req) {
        String category = req.getParameter("category");
        String content = req.getParameter("content");
        if(category.equals("title")) return responseService.getListResult(boardService.selectListByTitle(content));
        else if(category.equals("content")) return responseService.getListResult(boardService.selectListByContent(content));
        else return responseService.getListResult(boardService.selectListByUser(content));

    }
}
