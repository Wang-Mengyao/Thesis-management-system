package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Papcomment;
import com.example.demo.service.PapcommentService;
import com.example.demo.mapper.PapcommentMapper;
import org.springframework.stereotype.Service;

/**
* @author qqy20001120
* @description 针对表【sys_papcomment(论文评论)】的数据库操作Service实现
* @createDate 2023-06-09 23:39:52
*/
@Service
public class PapcommentServiceImpl extends ServiceImpl<PapcommentMapper, Papcomment>
    implements PapcommentService{

}




