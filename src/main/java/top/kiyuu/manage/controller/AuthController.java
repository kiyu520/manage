package top.kiyuu.manage.controller;

import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.service.UserService;
import top.kiyuu.manage.util.RESTBean;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    UserService userService;
    @Resource
    PasswordEncoder PasswordEncoder;


    //@PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @PostMapping("/register")
    public String register(String username,String password,Integer role_id){
        userService.save(new User(username,role_id,PasswordEncoder.encode(password),"测试部","123123123",LocalDateTime.now()));
        return "success";
    }

    @GetMapping("/info")
    public RESTBean<Map<String,Object>> info(Authentication authentication){
        User u= (User) authentication.getPrincipal();
        Map<String,Object> map = userService.getInfo(u.getUsername());
        if (map.isEmpty())return RESTBean.fail(400,"信息不存在");
        else return RESTBean.success(map);
    }

    @PostMapping("/password")
    public RESTBean<String> changePassword(@RequestBody Map<String,String> map,Authentication authentication){
        return userService.changePassword(map,authentication,PasswordEncoder);
    }
}
