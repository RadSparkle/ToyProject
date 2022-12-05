package com.toyproject.api.contents.board.service.impl;

import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.mapper.BoardMapper;
import com.toyproject.api.contents.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
}
