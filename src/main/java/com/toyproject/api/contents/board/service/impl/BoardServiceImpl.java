package com.toyproject.api.contents.board.service.impl;

import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.contents.board.mapper.BoardMapper;
import com.toyproject.api.contents.board.repository.BoardRepository;
import com.toyproject.api.contents.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public HashMap findPost(int bid) {
        HashMap boardMap = new HashMap();

        List<BoardDto.boardList> boardList = boardMapper.selectPost(bid);
        HashMap boardCount = boardMapper.getBoardCount(bid);

        boardMap.put("boardList",boardList);
        boardMap.put("boardCount",boardCount);

        return boardMap;
    }

    @Override
    public void addPost(BoardDto.boardInfo boardDto) {
        boardMapper.insertPost(boardDto);
        boardMapper.insertPostInfo(boardDto);
    }

    @Override
    public BoardDto.boardInfo findPostInfo(int pid, int bid) {
        return boardMapper.selectPostInfo(pid, bid);
    }

    @Override
    public void removePost(BoardDto.boardList boardInfo) {
        boardMapper.deletePost(boardInfo);
        boardMapper.deletePostInfo(boardInfo);
    }

    @Override
    public void addLike(BoardDto.boardLike boardInfo) {
        boardMapper.insertLike(boardInfo);
    }

    @Override
    public void addUnlike(BoardDto.boardLike boardInfo) {
        boardMapper.insertUnlike(boardInfo);
    }

    @Override
    public void modifyPost(BoardDto.boardInfo boardInfo) {
        boardMapper.updatePost(boardInfo);
        boardMapper.updatePostInfo(boardInfo);
    }

    @Override
    public void removePostMultiple(BoardDto.boardList boardInfo) {
        boardMapper.deletePostMultiple(boardInfo);
        boardMapper.deletePostInfoMultiple(boardInfo);
    }

    @Override
    public void addComment(BoardDto.boardCmt boardCmt) {
        boardMapper.insertComment(boardCmt);
    }

    @Override
    public List findComment(int pid, int bid) {
//        HashMap cmtMap = new HashMap();
//        List<BoardDto.boardCmt> cmtList = boardMapper.getCmt(pid, bid);
//        cmtMap.put("commentList",cmtList);
        List comments = boardRepository.findAllByPidAndBid(pid, bid);
        return comments;
    }

    @Override
    public HashMap findCategory() {
        HashMap hm = new HashMap();
        List<BoardDto.boardCategory> category = boardMapper.selectCategory();
        hm.put("category",category);

        return hm;
    }
}
