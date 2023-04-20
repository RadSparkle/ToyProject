package com.toyproject.api.contents.board.mapper;

import com.toyproject.api.contents.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<BoardDto.boardList> selectPost(int bid);

    HashMap getBoardCount(int bid);

    BoardDto.boardInfo selectPostInfo(int pid, int bid);

    void insertPostInfo(BoardDto.boardInfo boardDto);

    void insertPost(BoardDto.boardInfo boardDto);

    void deletePost(BoardDto.boardList boardInfo);

    void deletePostInfo(BoardDto.boardList boardInfo);

    void insertLike(BoardDto.boardLike boardInfo);

    void insertUnlike(BoardDto.boardLike boardInfo);

    void updatePost(BoardDto.boardInfo boardInfo);

    void updatePostInfo(BoardDto.boardInfo boardInfo);

    void deletePostMultiple(BoardDto.boardList boardInfo);

    void deletePostInfoMultiple(BoardDto.boardList boardInfo);

    void insertComment(BoardDto.boardCmt boardCmt);

    List<BoardDto.boardCmt> selectComment(int pid, int bid);

    List<BoardDto.boardCategory> selectCategory();
}
