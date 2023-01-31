package com.toyproject.api.contents.board.service.impl;

import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.mapper.BoardMapper;
import com.toyproject.api.contents.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public HashMap getBoardList(int bid) {
        HashMap boardMap = new HashMap();

        List<BoardDto.boardList> boardList = boardMapper.getBoardList(bid);
        HashMap boardCount = boardMapper.getBoardCount(bid);

        boardMap.put("boardList",boardList);
        boardMap.put("boardCount",boardCount);

        return boardMap;
    }

    @Override
    public void insertBoard(BoardDto.boardInfo boardDto) {
        boardMapper.insertBoard(boardDto);
        boardMapper.insertBoardInfo(boardDto);
    }

    @Override
    public BoardDto.boardInfo getBoardInfo(int pid, int bid) {
        return boardMapper.getBoardInfo(pid, bid);
    }

    @Override
    public void deleteBoardInfo(BoardDto.boardList boardInfo) {
        System.out.println(boardInfo);
        boardMapper.deleteBoard(boardInfo);
        boardMapper.deleteBoardInfo(boardInfo);
    }

    @Override
    public void insertLike(BoardDto.boardLike boardInfo) {
        System.out.println(boardInfo);
        boardMapper.insertLike(boardInfo);
    }

    @Override
    public void insertUnlike(BoardDto.boardLike boardInfo) {
        boardMapper.insertUnlike(boardInfo);
    }

    @Override
    public void updateBoard(BoardDto.boardInfo boardInfo) {
        boardMapper.updateBoard(boardInfo);
        boardMapper.updateBoardInfo(boardInfo);
    }

    @Override
    public void deleteBoardInfoMulti(BoardDto.boardList boardInfo) {
        boardMapper.deleteBoardMulti(boardInfo);
        boardMapper.deleteBoardInfoMulti(boardInfo);
    }

    @Override
    public void insertCmt(BoardDto.boardCmt boardCmt) {
        boardMapper.insertCmt(boardCmt);
    }


}
