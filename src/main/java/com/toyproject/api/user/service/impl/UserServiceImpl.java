package com.toyproject.api.user.service.impl;

import com.toyproject.api.user.dto.UserDto;
import com.toyproject.api.user.mapper.UserMapper;
import com.toyproject.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public HashMap findMyPage(int uid) {
        HashMap hm = new HashMap();

        UserDto.myPageInfo mypageInfo = userMapper.selectMyPage(uid);
        List boardList = userMapper.selectMyBoardList(uid);

        hm.put("myInfo",mypageInfo);
        hm.put("myBoardList",boardList);

        return hm;
    }

    @Override
    public void addFollow(int uid, int fid) {
        userMapper.insertFollow(uid, fid);
    }
}
