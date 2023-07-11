package com.example.demo.controller.DTO;

import lombok.Data;

import java.time.Year;

/**
 * 与前端上传、下载文献相关的参数
 */
@Data
public class PaperDTO {
    private String paperid;
    private String libraryid;
    private String title;
    private String year;
    private String author;
    private String publisher;
    private String source;
}
