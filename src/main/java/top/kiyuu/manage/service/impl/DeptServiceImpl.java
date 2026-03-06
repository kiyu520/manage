package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.Dept;
import top.kiyuu.manage.mapper.DeptMapper;
import top.kiyuu.manage.service.DeptService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Resource
    DeptMapper deptMapper;

    @Override
    public List<String> getDeptList() {
        return deptMapper.selectList(null).stream().map(Dept::getDeptName).toList();
    }
}
