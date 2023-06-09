package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Libcomment;
import com.example.demo.service.LibcommentService;
import com.example.demo.mapper.LibcommentMapper;
import org.springframework.stereotype.Service;

/**
* @author qqy20001120
* @description 针对表【sys_libcomment(文献库评论区)】的数据库操作Service实现
* @createDate 2023-06-09 00:24:06
*/
@Service
public class LibcommentServiceImpl extends ServiceImpl<LibcommentMapper, Libcomment>
    implements LibcommentService{

}




