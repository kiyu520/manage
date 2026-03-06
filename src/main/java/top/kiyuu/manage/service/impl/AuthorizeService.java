package top.kiyuu.manage.service.impl;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.mapper.UserMapper;

import java.time.LocalDateTime;

@Service
public class AuthorizeService implements UserDetailsService {
    @Resource
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.selectByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(user);
        return user;
    }
}
