package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @GetMapping("/{userId}/firstLibrary")
    public Result firstLibrary(@PathVariable String userId){
        if(StringUtils.isBlank(userId)){
            return Result.error(Constants.CODE_400,"参数错误");
        } else {
            Integer uid = Integer.valueOf(userId);
            QueryWrapper<Library> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", uid);
            try {
                Library one = libraryService.getOne(queryWrapper,false);
                return Result.success(one);
            } catch (Exception e) {
                return Result.error(Constants.CODE_400, "参数错误");
            }
        }
    }


    /**
     * 修改文献库信息
     * @param libraryDTO
     * @return
     */
    @PutMapping("/modify")
    public Result updateLibrary(@RequestBody LibraryDTO libraryDTO){
        Library library = new Library();
        library.setLibraryId(Integer.valueOf(libraryDTO.getLibrary_id()));
        //library.setUserId(Integer.valueOf(libraryDTO.getUser_id()));
        library.setTopic(libraryDTO.getTopic());
        library.setDes(libraryDTO.getDesc());
        if(Objects.equals(libraryDTO.getIs_public(), "true")){
            library.setIsPublic(1);
        } else {
            library.setIsPublic(0);
        }

        Library one = libraryMapper.selectById(library.getLibraryId());
        if(one==null) {
            return Result.error(Constants.CODE_400,"参数错误");
        } else {
            library.setUserId(one.getUserId());
            return Result.success(libraryMapper.updateById(library));
        }
    }

    /**
     * 增加文献库
     * @param libraryDTO
     * @return
     */
    @PutMapping("/insert")
    public Result insertLibrary(@RequestBody LibraryDTO libraryDTO) {
        Library library = new Library();
        library.setUserId(Integer.valueOf(libraryDTO.getUser_id()));
        library.setTopic(libraryDTO.getTopic());
        library.setDes(libraryDTO.getDesc());
        if(Objects.equals(libraryDTO.getIs_public(), "true")){
            library.setIsPublic(1);
        } else {
            library.setIsPublic(0);
        }
        libraryMapper.insert(library);
        return Result.success(library.getLibraryId());
    }

    /**
     * 删除文献库
     * @param library_id
     * @return
     */
    @DeleteMapping("/{library_id}")
    public Result deleteLibrary(@PathVariable Integer library_id) {
        // lid = library_id;
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("library_id", library_id);
        paperMapper.delete(queryWrapper);
        return Result.success(libraryService.removeById(library_id));
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable String pageNum, @PathVariable String pageSize) {
        Integer num = Integer.valueOf(pageNum);
        Integer size = Integer.valueOf(pageSize);
        Page<Library> page = libraryService.findPage(new Page<>(num,size),"");
        return Result.success(page);
    }

    @GetMapping("/page/{pageNum}/{pageSize}/{searchTerm}")
    public Result findPage(@PathVariable String pageNum, @PathVariable String pageSize,
                           @PathVariable String searchTerm) {
        Integer num = Integer.valueOf(pageNum);
        Integer size = Integer.valueOf(pageSize);
        Page<Library> page = libraryService.findPage(new Page<>(num,size),searchTerm);
        return Result.success(page);
    }
}
