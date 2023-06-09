package com.example.demo.service;

import com.example.demo.controller.DTO.PaperDTO;
import com.example.demo.pojo.Paper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author qqy20001120
* @description 针对表【sys_paper】的数据库操作Service
* @createDate 2023-06-07 22:34:21
*/
public interface PaperService extends IService<Paper> {



    String upload(PaperDTO paperDTO, MultipartFile file) throws IOException;
}
