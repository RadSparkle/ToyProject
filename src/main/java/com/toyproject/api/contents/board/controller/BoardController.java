package com.toyproject.api.contents.board.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ApiOperation("게시글 리스트 조회")
    @GetMapping("/boards/{bid}/posts")
    public ResponseEntity<Object> getBoardPost(@PathVariable int bid) {
        HashMap boardList = boardService.getBoardList(bid);

        return DefaultResponse.from(OK.value(),"게시글 리스트 조회 성공", boardList).build();
    }

    @ApiOperation("게시글 등록")
    @Transactional
    @PostMapping("/boards")
    public ResponseEntity<Object> createBoard(@RequestBody BoardDto.boardInfo boardDto) {
        boardService.insertBoard(boardDto);

        return DefaultResponse.from(CREATED.value(),"게시글 생성 성공", boardDto).build();
    }

    @ApiOperation("게시글 수정")
    @Transactional
    @PutMapping("/boards")
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
    @DeleteMapping("/boards")
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
    @PostMapping("/likes")
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

    @ApiOperation("댓글등록")
    @PostMapping("/comments")
    public ResponseEntity<Object> createComment(@RequestBody BoardDto.boardCmt boardCmt) {
        try {
            boardService.insertCmt(boardCmt);
        } catch (Exception e) {
            return DefaultResponse.from(BAD_REQUEST.value(),"댓글 등록 실패",boardCmt).build();
        }
        return DefaultResponse.from(OK.value(), "댓글 등록 완료", boardCmt).build();
    }

    @ApiOperation("댓글리스트 가져오기")
    @GetMapping("/boards/{bid}/comments/{pid}")
    public ResponseEntity<Object> getComments(@PathVariable int pid, @PathVariable int bid) {
        List boardCmtList = boardService.getCmt(pid, bid);
        return DefaultResponse.from(OK.value(), "댓글 리스트 조회완료", boardCmtList).build();
    }

//    @ApiOperation("댓글 삭제")
//    @DeleteMapping("/deleteComment")
//    public ResponseEntity<Object> deleteCmt(@RequestBody)

    @ApiOperation("전체 게시판 카테고리 조회")
    @GetMapping("/categories")
    public ResponseEntity<Object> getAllCategories() {
        HashMap boardCategory = boardService.getCategory();
        System.out.println(boardCategory);
        return DefaultResponse.from(OK.value(), "전체 카테고리 조회완료", boardCategory).build();
    }
}
