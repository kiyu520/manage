package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.Role;
import top.kiyuu.manage.mapper.RoleMapper;
import top.kiyuu.manage.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {
}
