package com.yzm.security.impl;

import com.yzm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Auther: yzm
 * @Date: 2021/6/16 - 06 - 16 - 16:39
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    /*    if(!username.equals("admin")){
            throw new UsernameNotFoundException("用户不存在！");
        }
        //从数据库查询出密码。
        String password= "pwd";
        String encodepassword = passwordEncoder.encode(password);*/

        com.yzm.pojo.User user = userMapper.selectByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }

        UserDetails userDetails = new User(username,user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin1,admin2"));
        return userDetails;
    }
}
