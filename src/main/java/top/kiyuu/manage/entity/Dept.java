package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dept")
@Data
public class Dept {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("dept_name")
    private String deptName;
}
