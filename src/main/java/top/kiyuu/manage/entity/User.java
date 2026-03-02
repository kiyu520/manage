package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
@AllArgsConstructor
public class User {
    @TableId // 假设 id 自增
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


}
