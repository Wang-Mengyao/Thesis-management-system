package com.example.demo.controller;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.DTO.LibcmtDTO;
import com.example.demo.mapper.LibcommentMapper;
import com.example.demo.pojo.Libcomment;
import com.example.demo.service.LibcommentService;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library-comment")
public class LibcommentController {

    private static final Log LOG = Log.get();
    @Resource
    private LibcommentService libcommentService;

    @Resource
    private LibcommentMapper libcommentMapper;

    @PostMapping("/post")
    public Result uploadCommentForLibrary(@RequestBody LibcmtDTO libcmtDTO) {

        Libcomment libcomment = new Libcomment();
        BeanUtils.copyProperties(libcmtDTO,libcomment);
        libcomment.setLibraryId(Integer.valueOf(libcmtDTO.getLibrary_id()));
        try {
            if (libcommentService.save(libcomment)) {
                return Result.success();
            } else {
                return Result.error(Constants.CODE_500, "内部故障");
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        return Result.error();
    }

    @GetMapping("/{libraryId}")
    public Result fetchCommentForLibrary(@PathVariable String libraryId) {
        if(StringUtils.isBlank(libraryId)) {
            return Result.error(Constants.CODE_400,"参数错误");
        } else {
            Integer libId = Integer.valueOf(libraryId);
            QueryWrapper<Libcomment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("library_id", libId);
            return Result.success(libcommentMapper.selectList(queryWrapper));
        }

    }
}
