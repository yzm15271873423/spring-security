package com.yzm.mapper;

import com.yzm.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 18:40
 */
@Mapper
public interface UserMapper {
    public User selectByUsername(String username);
}
