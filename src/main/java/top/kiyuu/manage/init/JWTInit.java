package top.kiyuu.manage.init;


import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.kiyuu.manage.util.JWTUtil;

@Component
public class JWTInit implements CommandLineRunner {
    @Resource
    JWTUtil jwtUtil;
    @Override
    public void run(String... args) throws Exception {
        jwtUtil.init();
    }
}
