package com.toyproject.api.contents.board.controller;

import com.toyproject.api.contents.board.dto.BoardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BoardController {
    ResponseEntity<Object> postList(@PathVariable int bid);

    ResponseEntity<Object> postAdd(@RequestBody BoardDto.boardInfo boardDto);

    ResponseEntity<Object> postModify(@RequestBody BoardDto.boardInfo boardInfo);

    ResponseEntity<Object> postDetails(@PathVariable int pid, @PathVariable int bid);

    ResponseEntity<Object> postRemove(@RequestBody BoardDto.boardList boardInfo);

    ResponseEntity<Object> postRemoveMultiple(@RequestBody BoardDto.boardList boardInfo);

    ResponseEntity<Object> postLike(@RequestBody BoardDto.boardLike boardInfo);

    ResponseEntity<Object> commentAdd(@RequestBody BoardDto.boardCmt boardCmt);

    ResponseEntity<Object> commentList(@PathVariable int pid, @PathVariable int bid);

    ResponseEntity<Object> categoryList();
}