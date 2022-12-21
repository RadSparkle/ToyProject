package com.toyproject.api.user.mapper;

import com.toyproject.api.contents.board.dto.BoardDto;
import com.toyproject.api.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.Cookie;
import java.util.List;

@Mapper
public interface UserMapper {
    UserDto.myPageInfo getMyPage(int uid);

    List getMyBoardList(int uid);
}
