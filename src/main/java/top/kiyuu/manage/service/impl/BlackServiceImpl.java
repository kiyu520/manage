package top.kiyuu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.kiyuu.manage.entity.BlackList;
import top.kiyuu.manage.mapper.BlackListMapper;
import top.kiyuu.manage.service.BlackListService;

@Service
public class BlackServiceImpl extends ServiceImpl<BlackListMapper,BlackList> implements BlackListService {
}
