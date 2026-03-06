package top.kiyuu.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.Dept;

import java.util.List;

@Service
public interface DeptService extends IService<Dept> {
    public List<String> getDeptList();
}
