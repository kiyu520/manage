package top.kiyuu.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.Category;

import java.util.List;

@Service
public interface CategoryService extends IService<Category> {
    public List<String> getCategoryNameList();
    public Category getCategoryByName(String CategoryName);
    public Category updateCategory(int id,String categoryName);
}
