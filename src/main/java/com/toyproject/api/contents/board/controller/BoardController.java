package com.toyproject.api.contents.board.controller;

import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/getBoardList")
    public ResponseEntity<Object> getBoardList(@RequestParam int bid) {
        HashMap boardList = boardService.getBoardList(bid);

        return ResponseEntity.ok(boardList);
    }

    @PostMapping("/insertBoard")
    public ResponseEntity<Object> insertBoard(@RequestBody BoardDto.boardInfo boardDto) {
        boardService.insertBoard(boardDto);

        return ResponseEntity.ok(boardDto);
    }

    @GetMapping("/getBoardInfo/{bid}/{pid}")
    public ResponseEntity<HashMap> getBoardInfo(@PathVariable int pid
            , @PathVariable int bid) {
        HashMap boardInfo = boardService.getBoardInfo(pid, bid);

        return ResponseEntity.ok(boardInfo);
    }
}
