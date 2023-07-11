package com.example.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Library;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author qqy20001120
* @description 针对表【sys_library(文献库数据)】的数据库操作Mapper
* @createDate 2023-06-07 22:01:24
* @Entity com.example.demo.pojo.Library
*/
public interface LibraryMapper extends BaseMapper<Library> {

    Page<Library> findPage(Page<Library> page, @Param("searchItem") String searchItem);
}




