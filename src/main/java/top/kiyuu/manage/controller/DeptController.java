package top.kiyuu.manage.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.kiyuu.manage.service.DeptService;
import top.kiyuu.manage.util.RESTBean;

import java.util.List;

@RestController
@RequestMapping("/api/dept")
public class DeptController {
    @Resource
    DeptService deptService;
    @GetMapping("/")
    public RESTBean<List<String>>  getDeptList(){
        List<String> deptList = deptService.getDeptList();
        if (deptList.isEmpty())return RESTBean.fail(400,"数据为空");
        else return RESTBean.success(deptList);
    }
}
