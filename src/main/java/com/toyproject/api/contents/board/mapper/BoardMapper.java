package com.toyproject.api.contents.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List getBoardList();
}
