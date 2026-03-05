package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("material")
public class Material {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("mat_name")
    private String matName;
    @TableField("mat_type")
    private String matType;
    @TableField("spec")
    private String spec;
    @TableField("unit")
    private String unit;
    @TableField("stock_num")
    private Integer stockNum;
    @TableField("price")
    private BigDecimal price;
    @TableField("remark")
    private String remark;
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("create_user")
    private Integer createUser;
    @TableField("category_id")
    private Integer categoryId;
}
