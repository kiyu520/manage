package top.kiyuu.manage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("blacklist")
@Data
@AllArgsConstructor
public class BlackList {
    @TableId
    private String token;
}
