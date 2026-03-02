package top.kiyuu.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.kiyuu.manage.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from management.user where user_name=#{username}")
    User selectByUsername(String username);
}
