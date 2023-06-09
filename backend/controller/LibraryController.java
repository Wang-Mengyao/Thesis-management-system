package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.DTO.LibraryDTO;
import com.example.demo.mapper.LibraryMapper;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.pojo.Library;
import com.example.demo.pojo.Paper;
import com.example.demo.service.LibraryService;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Resource
    private LibraryService libraryService;

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private LibraryMapper libraryMapper;

    /**
     * 获取文献库文献
     * @param libraryId
     * @return
     */
    @PostMapping("/{libraryId}/paper")
    public Result fetchPaperList(@PathVariable String libraryId) {
        if(StringUtils.isBlank(libraryId)) {
            return Result.error(Constants.CODE_400, "参数错误");
        } else {
            Integer lid = Integer.valueOf(libraryId);
            QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("library_id", lid);
            return Result.success(paperMapper.selectList(queryWrapper));
        }
    }

    /**
     * 获取文献库描述
     * @param libraryId
     * @return
     */
    @GetMapping("/{libraryId}/desc")
    public Result getLibraryDesc(@PathVariable String libraryId) {
        if(StringUtils.isBlank(libraryId)) {
            return  Result.error(Constants.CODE_400,"参数错误");
        } else {
            Integer lid = Integer.valueOf(libraryId);
            Library library = libraryMapper.selectById(lid);
            return Result.success(library.getDes());
        }
    }

    /**
     * 获取用户的文献库列表
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public Result fetchLibraryList(@PathVariable String userId) {
        if(StringUtils.isBlank(userId)){
            return Result.error(Constants.CODE_400,"参数错误");
        } else {
            Integer uid = Integer.valueOf(userId);
            QueryWrapper<Library> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", uid);
            return Result.success(libraryMapper.selectList(queryWrapper));
        }
    }


    /**
     * 修改文献库信息
     * @param libraryDTO
     * @return
     */
    @PutMapping
    public Result updateLibraryEntry(@RequestBody LibraryDTO libraryDTO){
        Library library = new Library();
        BeanUtils.copyProperties(libraryDTO,library);
        Library one = libraryMapper.selectById(library.getLibraryId());
        if(one==null) {
            return Result.error(Constants.CODE_400,"参数错误");
        } else {
            libraryMapper.updateById(library);
            return Result.success();
        }
    }
}
