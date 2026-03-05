package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("role")
@Data
public class Role {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField("role_name")
    String roleName;
    @TableField("menu_auth")
    String menuAuth;
    @TableField("dept_ids")
    String deptIds;
}
