package com.toyproject.api.contents.board.service;


import com.toyproject.api.contents.board.dto.BoardDto;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface BoardService {
    HashMap findPost(int bid);

    void addPost(BoardDto.boardInfo boardDto);

    BoardDto.boardInfo findPostInfo(int pid, int bid);

    void removePost(BoardDto.boardList boardInfo);

    void addLike(BoardDto.boardLike boardInfo);

    void addUnlike(BoardDto.boardLike boardInfo);

    void modifyPost(BoardDto.boardInfo boardInfo);

    void removePostMultiple(BoardDto.boardList boardInfo);

    void addComment(BoardDto.boardCmt boardCmt);

    List findComment(int pid, int bid);

    HashMap findCategory();
}
