package com.toyproject.api.contents.board.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ApiOperation("게시글 리스트 조회")
    @GetMapping("/getBoard/{bid}")
    public ResponseEntity<Object> getBoardList(@PathVariable int bid) {
        HashMap boardList = boardService.getBoardList(bid);

        return DefaultResponse.from(OK.value(),"게시글 리스트 조회 성공", boardList).build();
    }

    @ApiOperation("게시글 등록")
    @Transactional
    @PostMapping("/insertBoard")
    public ResponseEntity<Object> insertBoard(@RequestBody BoardDto.boardInfo boardDto) {
        boardService.insertBoard(boardDto);

        return DefaultResponse.from(CREATED.value(),"게시글 생성 성공", boardDto).build();
    }

    @ApiOperation("게시글 수정")
    @Transactional
    @PutMapping("/updateBoard")
    public ResponseEntity<Object> updateBoard(@RequestBody BoardDto.boardInfo boardInfo) {
        boardService.updateBoard(boardInfo);

        return DefaultResponse.from(OK.value(),"게시글 수정 성공", boardInfo).build();
    }

    @ApiOperation("게시글 상세페이지 조회")
    @GetMapping("/getBoard/{bid}/{pid}")
    public ResponseEntity<Object> getBoardInfo(@PathVariable int pid
            , @PathVariable int bid) {
        BoardDto.boardInfo boardInfo = boardService.getBoardInfo(pid, bid);

        if(boardInfo == null) {
            return DefaultResponse.from(NOT_FOUND.value(),"존재하지않는 게시글 입니다.",boardInfo).build();
        }

        return DefaultResponse.from(OK.value(),"게시글 상세 조회 성공", boardInfo).build();
    }

    @ApiOperation("게시글 삭제")
    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Object> deleteBoard(@RequestBody BoardDto.boardList boardInfo) {
        //게시글 단일 삭제
        if(boardInfo.getPidList() == null) {
            boardService.deleteBoardInfo(boardInfo);
        }

        //게시글 다중 삭제
        if(boardInfo.getPid() == 0) {
            deleteBoardMulti(boardInfo);
        }

        return DefaultResponse.from(OK.value(),"게시글 삭제 성공", boardInfo).build();
    }

    @ApiOperation("게시글 다중삭제 객체")
    public ResponseEntity<Object> deleteBoardMulti(@RequestBody BoardDto.boardList boardInfo) {
        boardService.deleteBoardInfoMulti(boardInfo);

        return DefaultResponse.from(OK.value(),"게시글 다중 삭제 성공", boardInfo).build();
    }

    @ApiOperation("게시글 추천버튼")
    @PostMapping("/like")
    public ResponseEntity<Object> likeBoard(@RequestBody BoardDto.boardLike boardInfo) {
        int likeType = boardInfo.getLikeType();

        try {
            switch (likeType) {
                case 1:
                    boardService.insertLike(boardInfo);
                    return DefaultResponse.from(OK.value(), "추천 성공", boardInfo).build();

                case 0:
                    boardService.insertUnlike(boardInfo);
                    return DefaultResponse.from(OK.value(), "비추천 성공", boardInfo).build();

                default:
                    return DefaultResponse.from(BAD_REQUEST.value(), "likeType이 비정상적임", boardInfo).build();
            }
        } catch (DuplicateKeyException e) {
            return DefaultResponse.from(BAD_REQUEST.value(), "이미 추천을 하였습니다.",boardInfo).build();
        }
    }
}
