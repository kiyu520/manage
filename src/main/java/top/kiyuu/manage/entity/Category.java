package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("category")
@Data
@AllArgsConstructor
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("category_name")
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
