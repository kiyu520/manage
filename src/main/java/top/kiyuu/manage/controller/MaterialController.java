package top.kiyuu.manage.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.kiyuu.manage.entity.Material;
import top.kiyuu.manage.service.MaterialService;
import top.kiyuu.manage.util.RESTBean;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/material")
public class MaterialController {
    @Resource
    MaterialService materialService;
    @GetMapping("/")
    public RESTBean<List<Material>> getMaterial(@RequestHeader Map<String,String> map) {
       return RESTBean.success(materialService.queryMaterial(map));
    }

    @GetMapping("/{id}")
    public RESTBean<Material> getMaterialById(@PathVariable String id) {
        return RESTBean.success(materialService.getById(id));
    }

    @PostMapping("/")
    public RESTBean<String> addMaterial(@RequestBody Material material) {
        materialService.save(material);
        return RESTBean.success("添加成功");
    }
}
