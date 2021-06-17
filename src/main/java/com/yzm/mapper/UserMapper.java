package com.yzm.mapper;

import com.yzm.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 18:40
 */

public interface UserMapper {
    public User selectByUsername(String username);

    public List<String> selectAllRolesByUserid(Integer userId);

    public List<String> selectAllPermissionsByUserid(Integer userId);
}
