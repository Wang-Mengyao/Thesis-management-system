package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Library;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author qqy20001120
* @description 针对表【sys_library(文献库数据)】的数据库操作Service
* @createDate 2023-06-07 22:01:24
*/
public interface LibraryService extends IService<Library> {

    Page<Library> findPage(Page<Library> page, String searchItem);
}
