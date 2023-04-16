package com.toyproject.api.contents.board.repository;

import com.toyproject.api.contents.board.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardRepository extends JpaRepository<BoardDto.boardComments, Long> {
    List findAllByPidAndBid(int pid, int bid);
}
