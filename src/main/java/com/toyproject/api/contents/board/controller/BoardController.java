package com.toyproject.api.contents.board.controller;

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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList/{bid}")
    public ResponseEntity<Object> getBoardList(@PathVariable int bid) {
        HashMap boardList = boardService.getBoardList(bid);

        return ResponseEntity.ok(boardList);
    }

    @Transactional
    @PostMapping("/insertBoard")
    public ResponseEntity<Object> insertBoard(@RequestBody BoardDto.boardInfo boardDto) {
        System.out.println(boardDto);
        boardService.insertBoard(boardDto);

        return ResponseEntity.ok(boardDto);
    }

    @GetMapping("/getBoardInfo/{bid}/{pid}")
    public ResponseEntity<Object> getBoardInfo(@PathVariable int pid
            , @PathVariable int bid) {
        BoardDto.boardInfo boardInfo = boardService.getBoardInfo(pid, bid);

        return ResponseEntity.ok(boardInfo);
    }
}
