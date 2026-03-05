package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.kiyuu.manage.entity.Material;
import top.kiyuu.manage.mapper.MaterialMapper;
import top.kiyuu.manage.service.MaterialService;

import java.util.List;
import java.util.Map;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {
    @Resource
    MaterialMapper materialMapper;
    public List<Material> queryMaterial(Map<String,String> map){
        LambdaQueryWrapper<Material> queryWrapper = new LambdaQueryWrapper<>();
        Page<Material> page = new Page<>(Integer.parseInt(map.get("page")),Integer.parseInt(map.get("pageSize")));
        String keyword = map.get("keyword");
        String category = map.get("category");
        queryWrapper.like(StringUtils.hasText(keyword), Material::getMatName,keyword);
        queryWrapper.like(StringUtils.hasText(category),Material::getMatType,category);
        return materialMapper.selectList(queryWrapper);
    }
}
