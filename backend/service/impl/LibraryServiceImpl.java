package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.Library;
import com.example.demo.service.LibraryService;
import com.example.demo.mapper.LibraryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author qqy20001120
* @description 针对表【sys_library(文献库数据)】的数据库操作Service实现
* @createDate 2023-06-07 22:01:24
*/
@Service
public class LibraryServiceImpl extends ServiceImpl<LibraryMapper, Library>
    implements LibraryService{

    @Resource
    private LibraryMapper libraryMapper;

    @Override
    public Page<Library> findPage(Page<Library> page, String searchItem) {
        return libraryMapper.findPage(page, searchItem);
    }

}




