package top.kiyuu.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.kiyuu.manage.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select category_name from management.category")
    public List<String> getCategoryNames();

    @Select("select * from management.category where category_name=#{categoryName}")
    public Category getCategoryByName(String categoryName);

}
