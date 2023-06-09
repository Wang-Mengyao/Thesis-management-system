package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.DTO.PaperDTO;
import com.example.demo.pojo.Paper;
import com.example.demo.service.PaperService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    /**
     * 文献上传
     * @param libraryId
     * @param title
     * @param year
     * @param author
     * @param publisher
     * @param source
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result paperUpload(@RequestParam("library_id") Integer libraryId, @RequestParam("title") String title,
                              @RequestParam(name = "year",required = false) Year year, @RequestParam(name="author",required = false) String author,
                              @RequestParam(name="publisher",required = false) String publisher, @RequestParam(name="source",required = false) String source,
                              @RequestParam(name="file",required = false) MultipartFile file) throws IOException {
        PaperDTO paperDTO = new PaperDTO();
        paperDTO.setLibraryid(libraryId);
        paperDTO.setTitle(title);
        paperDTO.setYear(year);
        paperDTO.setAuthor(author);
        paperDTO.setPublisher(publisher);
        paperDTO.setSource(source);
        if(StringUtils.isBlank(title)||libraryId<=0) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(paperService.upload(paperDTO, file));
    }

    /**
     * 文件下载接口 "http://localhost:9090/paper/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void paperDownload(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件唯一标识符获取文件
        File uploadFile = new File(fileUploadPath+fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileUUID, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(paperService.removeById(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(paperService.removeByIds(ids));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "title") String selectType,
                           @RequestParam String searchInput){
        IPage<Paper> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        switch(selectType){
            case "title" :
                queryWrapper.like("title", searchInput);
                break;
            case "year" :
                //语句
                break; //可选
            //你可以有任意数量的case语句
            default : //可选
                //语句
        }
        return Result.success(paperService.page(page, queryWrapper));
    }
}
