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

import java.util.List;

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

        //查询数据库
        com.yzm.pojo.User user = userMapper.selectByUsername(username);
        //判断用户是否为空
        if (user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }

        //从数据库中查询角色、权限
        List<String> roles = userMapper.selectAllRolesByUserid(user.getId());
        List<String> permissions = userMapper.selectAllPermissionsByUserid(user.getId());

        StringBuffer sf = new StringBuffer();
        for (String role : roles) {
            sf.append("ROLE_"+role+",");
        }

        for (String permission : permissions) {
            sf.append(permission+",");
        }

        //去除字符串最后的逗号
        String substring = sf.toString().substring(0, sf.toString().length() - 1);

        //比较登录用户和密码，是否与与数据库相匹配
        UserDetails userDetails = new User(username,user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(substring));
        return userDetails;
    }
}
