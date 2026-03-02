package top.kiyuu.manage.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {
    @Value("${jwt.key}")
    private String key;
    @Value("${jwt.past_time}")
    private int past_time;

    public String create(UserDetails user){
        Algorithm algorithm = Algorithm.HMAC256(key);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND,past_time);
        return JWT.create()
                .withClaim("name",user.getUsername())
                .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
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
                return User.withUsername(claims.get("name").asString())
                        .password("")
                        .authorities(claims.get("authorities").asArray(String.class))
                        .build();
            }
        }catch (Exception e) {
            return null;
        }
    }
}
