package top.kiyuu.manage.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.Material;

import java.util.List;
import java.util.Map;

@Service
public interface MaterialService extends IService<Material> {
    public List<Material> queryMaterial(Map<String,String> map);
}
