package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import top.kiyuu.manage.entity.Role;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.mapper.UserMapper;
import top.kiyuu.manage.service.RoleService;
import top.kiyuu.manage.service.UserService;
import top.kiyuu.manage.util.RESTBean;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RoleService roleService;

    public Map<String,Object> getInfo(String username){
        Map<String,Object> map = new HashMap<>();
        User user = userMapper.selectByUsername(username);
        Role role= roleService.getById(user.getRoleId());
        map.put("id",user.getId());
        map.put("user_name",user.getUsername());
        map.put("role_id",user.getRoleId());
        map.put("role_name",role.getRoleName());
        map.put("dept_name",user.getDeptName());
        map.put("phone",user.getPhone());
        map.put("last_login_time",user.getLastLoginTime());
        return map;
    }

    public RESTBean<String> changePassword(Map<String,String> map, Authentication authentication,PasswordEncoder passwordEncoder){
        String oldPwd = map.get("oldPwd");
        String newPwd = map.get("newPwd");
        String confirmPwd = map.get("confirmPwd");
        String username= ((User) authentication.getPrincipal()).getUsername();
        User u=userMapper.selectByUsername(username);
        if(confirmPwd==null||!confirmPwd.equals(newPwd))return RESTBean.fail(400,"校验错误");
        if (!passwordEncoder.matches(oldPwd,u.getPassword()))return RESTBean.fail(400,"校验错误");
        try {
            u.setPassword(passwordEncoder.encode(newPwd));
            userMapper.updateById(u);
            return RESTBean.success("修改成功",null);
        }catch (Exception e){
            return RESTBean.fail(400,"修改失败");
        }
    }

    public void login(String username){
        User user=userMapper.selectByUsername(username);
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateById(user);
    }
}
