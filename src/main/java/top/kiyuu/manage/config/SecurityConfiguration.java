package top.kiyuu.manage.config;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.kiyuu.manage.entity.User;
import top.kiyuu.manage.service.UserService;
import top.kiyuu.manage.util.JWTFilter;
import top.kiyuu.manage.util.JWTUtil;
import top.kiyuu.manage.util.RESTBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration {
    @Resource
    JWTUtil jwtUtil;
    @Resource
    JWTFilter jwtFilter;
    @Resource
    UserService userService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(conf ->{
                    CorsConfiguration cors= new CorsConfiguration();
                    cors.addAllowedOriginPattern("*");
                    cors.setAllowCredentials(true);  //允许跨域请求中携带Cookie
                    cors.addAllowedHeader("*");   //其他的也可以配置，为了方便这里就 * 了
                    cors.addAllowedMethod("*");
                    cors.addExposedHeader("*");
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);  //直接针对于所有地址生效
                    conf.configurationSource(source);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        //.requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> {
                    form.loginProcessingUrl("/api/auth/login");
                    form.successHandler(this::handleProcess);
                    form.failureHandler(this::handleProcess);
                    form.permitAll();
                })
                .logout(logout -> logout.logoutUrl("/api/auth/logout").logoutSuccessUrl("/").permitAll())
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(conf -> {
                    conf.accessDeniedHandler(this::handleProcess);
                    conf.authenticationEntryPoint(this::handleProcess);
                })
                .build();
    }

    private void handleProcess(HttpServletRequest request, HttpServletResponse response, Object exceptionOrAuthentication) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (exceptionOrAuthentication instanceof AccessDeniedException exception) {
            writer.write(RESTBean.fail(403,exception.getMessage(),null).toJsonString());
        }else if (exceptionOrAuthentication instanceof AuthenticationException exception) {
            writer.write(RESTBean.fail(401,exception.getMessage(),null).toJsonString());
        }else if (exceptionOrAuthentication instanceof Authentication authentication) {
            User u= (User) authentication.getPrincipal();
            writer.write(RESTBean.success(Map.of("token",jwtUtil.create(u),"userInfo",userService.getInfo(u.getUsername()))).toJsonString());
        }
    }

}
