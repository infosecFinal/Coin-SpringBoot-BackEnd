package com.rest.api.controller;

import com.rest.api.Service.BoardService;
import com.rest.api.Service.ResponseService;
import com.rest.api.VO.BoardVO;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api(tags = {"2. Board"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/board")
public class BoardController {

    private final BoardService boardService;
    private final ResponseService responseService;
    private final HttpSession session;

    @ApiOperation(value="게시글 전체 조회", notes="모든 게시글을 조회한다")
    @GetMapping(value="/lists")
    public ListResult<BoardVO> selectList() {
        List<BoardVO> result = boardService.selectBoardList();
        return responseService.getListResult(result);
    }

    @ApiOperation(value="게시글 작성", notes="게시글을 작성한다")
    @PostMapping(value="/insert")
    public SingleResult<Integer> writeBoard(@ApiParam(value = "게시글 작성") @RequestBody BoardVO boardVO) {
        if(session.getAttribute("id") == null) throw new RuntimeException();
        int res = boardService.writeBoard(boardVO);
        if(res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="게시글 수정", notes="게시글을 수정한다")
    @PostMapping(value="/update")
    public SingleResult<Integer> updateBoard(@ApiParam(value="게시글 수정") @RequestBody BoardVO boardVO) {
        String user = boardService.selectBoardListById(boardVO.getId()).getUser_id();
        if(user.equals(session.getAttribute("id"))) {
            int res = boardService.updateBoard(boardVO);
            if(res < 1) throw new RuntimeException();
            return responseService.getSingleResult(res);
        }
        else throw new RuntimeException();
    }

    @ApiOperation(value="게시글 삭제", notes="게시글을 비활성화 한다")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteBoard(@ApiParam(value="게시글 삭제") @RequestBody BoardVO boardVO) {
        int res = boardService.deleteBoard(boardVO);
        if(res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="게시글 조회", notes="게시글 번호로 조회한다.")
    @GetMapping(value="/list/{board_id}")
    public SingleResult<BoardVO> selectListById(@ApiParam(value="게시글 번호") @PathVariable int board_id) {
        BoardVO board = boardService.selectBoardListById(board_id);
        if(board == null) throw new RuntimeException();
        return responseService.getSingleResult(board);
    }

    @ApiOperation(value="게시글 검색", notes="게시글을 타입별로 검색한다.")
    @GetMapping(value="/find")
    public ListResult<BoardVO> findList(HttpServletRequest req) {
        String category = req.getParameter("category");
        String content = req.getParameter("content");
        System.out.println("contents: "+ content);
        System.out.println("category: " + category);
        List<BoardVO> board = new ArrayList<>();
        if(category.equals("title")) {
            board = boardService.selectListByTitle(content);
            System.out.println(board.toString());
            if(board == null) throw new RuntimeException();
            return responseService.getListResult(board);
        }
        else if(category.equals("content")){
            board = boardService.selectListByContent(content);
            if(board == null) throw new RuntimeException();
            return responseService.getListResult(board);
        }
        else{
            board = boardService.selectListByUser(content);
            if(board == null) throw new RuntimeException();
            return responseService.getListResult(board);
        }

    }
}
