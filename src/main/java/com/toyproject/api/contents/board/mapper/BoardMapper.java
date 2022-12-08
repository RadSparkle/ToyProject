package com.toyproject.api.contents.board.mapper;

import com.toyproject.api.contents.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto.boardList> getBoardList(int bid);

    HashMap getBoardCount(int bid);

    void insertBoardInfo(BoardDto.boardInfo boardDto);

    HashMap getBoardInfo(int pid, int bid);

    void insertBoard(BoardDto.boardInfo boardDto);
}
