package top.kiyuu.manage.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.kiyuu.manage.entity.BlackList;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.mapper.BlackListMapper;
import top.kiyuu.manage.service.BlackListService;
import top.kiyuu.manage.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JWTUtil {
    @Value("${jwt.key}")
    private String key;
    @Value("${jwt.past_time}")
    private int past_time;

    @Resource
    BlackListService  blackListService;

    private static  final HashSet<String> blacklist = new HashSet<>();
    public String create(UserDetails user){
        User u=(User)user;
        Algorithm algorithm = Algorithm.HMAC256(key);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND,past_time);
        return JWT.create()
                .withClaim("name",user.getUsername())
                .withClaim("roleId", u.getRoleId())
                .withIssuedAt(now)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    public UserDetails resolve(String token){
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> claims = jwt.getClaims();
            if (new Date().after(claims.get("exp").asDate())) {
                return null;
            }
            else {
                System.out.println(claims.get("name").asString());
                User u=new User();
                u.setUserName(claims.get("name").asString());
                u.setRoleId(claims.get("roleId").asInt());
                return u;
            }
        }catch (Exception e) {
            return null;
        }
    }

    public boolean ban(String token){
        if (blacklist.contains(token))return false;
        blacklist.add(token);
       blackListService.save(new BlackList(token));
       return true;
    }

    public void init(){
        blacklist.addAll(blackListService.list().stream().map(BlackList::getToken).collect(Collectors.toSet()));
    }
}
