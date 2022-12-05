package com.toyproject.api.contents.board.service.impl;

import com.toyproject.api.contents.board.mapper.BoardMapper;
import com.toyproject.api.contents.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List getBoardList() {
        System.out.println("test");
        return boardMapper.getBoardList();
    }
}
