package com.toyproject.api.user.service;


public interface UserService {
    Object getMyPage(int uid);

    void insertFollow(int uid, int fid);
}
