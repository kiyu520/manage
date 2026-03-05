package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.Category;
import top.kiyuu.manage.mapper.CategoryMapper;
import top.kiyuu.manage.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    public List<String> getCategoryNameList(){
        return categoryMapper.getCategoryNames();
    }

    public Category getCategoryByName(String CategoryName){
        return categoryMapper.getCategoryByName(CategoryName);
    }

    public Category updateCategory(int id,String categoryName){
        if (categoryMapper.selectById(id) == null){
            return null;
        }
        else {
            Category category=new Category(id,categoryName);
            categoryMapper.updateById(category);
            return category;
        }
    }
}
