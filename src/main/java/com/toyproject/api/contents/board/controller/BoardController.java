package com.toyproject.api.contents.board.controller;

import com.toyproject.api.common.DefaultResponse;
import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getBoard/{bid}")
    public ResponseEntity<Object> getBoardList(@PathVariable int bid) {
        HashMap boardList = boardService.getBoardList(bid);

        return DefaultResponse.from(OK.value(),"게시글 리스트 조회 성공", boardList).build();
    }

    @Transactional
    @PostMapping("/insertBoard")
    public ResponseEntity<Object> insertBoard(@RequestBody BoardDto.boardInfo boardDto) {
        boardService.insertBoard(boardDto);

        return DefaultResponse.from(CREATED.value(),"게시글 생성 성공", boardDto).build();
    }

    @GetMapping("/getBoard/{bid}/{pid}")
    public ResponseEntity<Object> getBoardInfo(@PathVariable int pid
            , @PathVariable int bid) {
        BoardDto.boardInfo boardInfo = boardService.getBoardInfo(pid, bid);

        if(boardInfo == null) {
            return DefaultResponse.from(NOT_FOUND.value(),"존재하지않는 게시글 입니다.",boardInfo).build();
        }

        return DefaultResponse.from(OK.value(),"게시글 상세 조회 성공", boardInfo).build();
    }

    @DeleteMapping("/deleteBoard")
    public ResponseEntity<Object> deleteBoard(@RequestBody BoardDto.boardInfo boardInfo) {
        boardService.deleteBoardInfo(boardInfo);

        return DefaultResponse.from(OK.value(),"게시글 삭제 성공", boardInfo).build();
    }
}
