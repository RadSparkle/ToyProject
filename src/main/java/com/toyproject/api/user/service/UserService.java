package com.toyproject.api.user.service;


public interface UserService {
    Object findMyPage(int uid);

    void addFollow(int uid, int fid);
}
