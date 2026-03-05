package top.kiyuu.manage.controller;

import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.kiyuu.manage.entity.Category;
import top.kiyuu.manage.service.CategoryService;
import top.kiyuu.manage.util.RESTBean;

import java.util.List;

@RestController("/api/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/")
    public RESTBean<List<String>> getCategory(){
        List<String> result=categoryService.getCategoryNameList();
        if (result.isEmpty())return RESTBean.fail(400,"查询失败");
        return RESTBean.success(result);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @PostMapping("/")
    public RESTBean<Category> addCategory(@RequestBody String category_name){
        if (categoryService.save(new Category(category_name)))return RESTBean.success("添加成功",categoryService.getCategoryByName(category_name));
        else return RESTBean.fail(500,"添加失败");
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public RESTBean<Category> updateCategory(@RequestBody String category_name, @PathVariable int id){
        Category category = categoryService.updateCategory(id, category_name);
        if (category==null)return RESTBean.fail(400,"id不存在");
        else return RESTBean.success(category);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public RESTBean<String> deleteCategory(@PathVariable int id){
        if (categoryService.removeById(id))return RESTBean.success("删除成功",null);
        else return RESTBean.fail(400,"删除失败");
    }
}
