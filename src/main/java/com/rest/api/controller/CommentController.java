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

import java.util.List;

@Api(tags={"4. Comment"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="comment")
public class CommentController {

    private final CommentService commentService;
    private final ResponseService responseService;

    @ApiOperation(value="댓글 입력", notes="DB에 댓글 내용 저장")
    @PostMapping(value="/insert")
    public SingleResult<Integer> insertComment(@ApiParam(value="댓글 입력") @RequestBody CommentVO comment) {
        System.out.println("comment");
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

}
