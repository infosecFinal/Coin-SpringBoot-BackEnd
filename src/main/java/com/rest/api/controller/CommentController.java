package com.rest.api.controller;


import com.rest.api.Service.CommentService;
import com.rest.api.Service.ResponseService;
import com.rest.api.VO.CommentVO;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags={"4. Comment"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="comment")
public class CommentController {

    private final CommentService commentService;
    private final ResponseService responseService;
    private final HttpSession session;

    @ApiOperation(value="댓글 입력", notes="DB에 댓글 내용 저장")
    @PostMapping(value="/insert")
    public SingleResult<Integer> insertComment(@ApiParam(value="댓글 입력") @RequestBody CommentVO comment) {
        if(session.getAttribute("id") == null) throw new RuntimeException();
        int res = commentService.insertComment(comment);
        System.out.println(res);
        if ( res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }

    @ApiOperation(value="댓글 출력", notes="DB에 저장된 해당 board의 댓글 출력")
    @GetMapping(value="/lists/{board_id}")
    public ListResult<CommentVO> selectCommentByBoard(@ApiParam(value="댓글 출력") @PathVariable int board_id) {
        List<CommentVO> list = commentService.selectCommentByBoard(board_id);
        if(list == null) throw new RuntimeException();
        return responseService.getListResult(list);
    }

    @ApiOperation(value="댓글 삭제", notes="DB에 저장된 해당 board의 댓글 삭제₩")
    @PostMapping(value="/delete")
    public SingleResult<Integer> deleteComment(@ApiParam(value="댓글 삭제") @RequestBody CommentVO comment) {
        if(comment.getUser_id().equals(session.getAttribute("id"))) throw new RuntimeException();
        int res = commentService.deleteComment(comment);
        if ( res < 1) throw new RuntimeException();
        return responseService.getSingleResult(res);
    }
}
