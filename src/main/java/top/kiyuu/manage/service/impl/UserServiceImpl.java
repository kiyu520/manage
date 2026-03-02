package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.mapper.UserMapper;
import top.kiyuu.manage.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
