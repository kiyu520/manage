package top.kiyuu.manage.service.impl;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.mapper.UserMapper;

@Service
public class AuthorizeService implements UserDetailsService {
    @Resource
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.selectByUsername(username);
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        if(user==null){
            System.out.println("1111111");
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword()).build();
    }
}
