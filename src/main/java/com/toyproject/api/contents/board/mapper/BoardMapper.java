package com.toyproject.api.contents.board.mapper;

import com.toyproject.api.contents.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto.boardList> getBoardList(int bid);

    HashMap getBoardCount(int bid);

    BoardDto.boardInfo getBoardInfo(int pid, int bid);

    void insertBoardInfo(BoardDto.boardInfo boardDto);

    void insertBoard(BoardDto.boardInfo boardDto);

    void deleteBoard(BoardDto.boardList boardInfo);

    void deleteBoardInfo(BoardDto.boardList boardInfo);

    void insertLike(BoardDto.boardLike boardInfo);

    void insertUnlike(BoardDto.boardLike boardInfo);

    void updateBoard(BoardDto.boardInfo boardInfo);

    void updateBoardInfo(BoardDto.boardInfo boardInfo);

    void deleteBoardMulti(BoardDto.boardList boardInfo);

    void deleteBoardInfoMulti(BoardDto.boardList boardInfo);
}
