package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_name")
    private String userName;

    @TableField("role_id")
    private Integer roleId;

    @TableField("password")
    private String password;

    @TableField("dept_name")
    private String deptName;

    @TableField("phone")
    private String phone;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    public User(String username, int i, String encode, String deptName, String number, LocalDateTime now) {
        this.userName = username;
        this.roleId = i;
        this.password = encode;
        this.phone = number;
        this.lastLoginTime = now;
        this.deptName = deptName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        if (roleId == 1) authorities.add("ROLE_SUPER_ADMIN");
        else if (roleId == 2) authorities.add("ROLE_ADMIN");
        else if (roleId == 3) authorities.add("ROLE_USER");
        else authorities.add("ROLE_GUEST");
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

}
