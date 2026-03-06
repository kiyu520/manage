package top.kiyuu.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.util.RESTBean;

import java.util.Map;

@Service
public interface UserService extends IService<User> {
    public Map<String,Object> getInfo(String username);
    public RESTBean<String> changePassword(Map<String,String> map, Authentication authentication, PasswordEncoder passwordEncoder);
    public void login(String username);
}
