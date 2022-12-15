package com.toyproject.api.contents.board.service;


import com.toyproject.api.contents.board.dto.BoardDto;

import java.util.HashMap;

public interface BoardService {
    HashMap getBoardList(int bid);

    void insertBoard(BoardDto.boardInfo boardDto);

    BoardDto.boardInfo getBoardInfo(int pid, int bid);

    void deleteBoardInfo(BoardDto.boardInfo boardInfo);

    void insertLike(BoardDto.boardLike boardInfo);

    void insertUnlike(BoardDto.boardLike boardInfo);
}
