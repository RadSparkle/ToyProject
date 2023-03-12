package com.toyproject.api.common.mapper.auth;

import com.toyproject.api.common.model.auth.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonAuthMapper {
    UserVo getUser(Long uid);
}
