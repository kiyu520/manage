package top.kiyuu.manage.controller;

import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.service.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource
    UserService userService;
    @Resource
    PasswordEncoder PasswordEncoder;
    @GetMapping("/test")
    public String test(){
        return "HelloWorld";
    }

    @PostMapping("/register")
    public String register(String username,String password){
        userService.save(new User(2,username,1,PasswordEncoder.encode(password),"测试部","123123123",LocalDateTime.now()));
        return "success";
    }
}
