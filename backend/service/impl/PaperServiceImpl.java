package com.example.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.DTO.PaperDTO;
import com.example.demo.exception.ServiceException;
import com.example.demo.pojo.Paper;
import com.example.demo.service.PaperService;
import com.example.demo.mapper.PaperMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
* @author qqy20001120
* @description 针对表【sys_paper】的数据库操作Service实现
* @createDate 2023-06-07 22:34:21
*/
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper>
    implements PaperService{

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    PaperMapper paperMapper;
    @Override
    public String upload(PaperDTO paperDTO, MultipartFile file) throws IOException {
        Paper one = getPaperInfo(paperDTO);
        if(one==null){
            one = new Paper();
            BeanUtils.copyProperties(paperDTO,one);
        } else {
            throw new ServiceException(Constants.CODE_600, "论文已存在");
        }
        // 存储文件到磁盘
        File uploadFileDir = new File(fileUploadPath);
        if(!uploadFileDir.exists()) {
            uploadFileDir.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid+".pdf";
        File uploadFile = new File(fileUploadPath+fileUUID);
        // 存储文件
        file.transferTo(uploadFile);

        // 存入数据库
        // pdf存的是文件url
        String url = "http://localhost:9090/paper/"+fileUUID;
        one.setPdf(url);
        one.setLibraryId(Integer.valueOf(paperDTO.getLibraryid()));
        paperMapper.insert(one);

        return url;
    }

    @Override
    public String uploadById(Integer paperid, MultipartFile file) throws IOException{
        Paper one = paperMapper.selectById(paperid);
        File uploadFileDir = new File(fileUploadPath);
        if(!uploadFileDir.exists()) {
            uploadFileDir.mkdirs();
        }
        // 定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid+".pdf";
        File uploadFile = new File(fileUploadPath+fileUUID);
        // 存储文件
        file.transferTo(uploadFile);

        // 存入数据库
        // pdf存的是文件url
        String url = "http://localhost:9090/paper/"+fileUUID;
        one.setPdf(url);
        paperMapper.updateById(one);
        return url;
    }

    private Paper getPaperInfo(PaperDTO paperDTO){
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("library_id", paperDTO.getLibraryid());
        queryWrapper.eq("title",paperDTO.getTitle());
        Paper one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}




