package com.toyproject.api.contents.board.service;

import com.toyproject.api.contents.board.dto.BoardDto;

import java.util.HashMap;
import java.util.List;

public interface BoardService {
    HashMap getBoardList(int bid);
}
