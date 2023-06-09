//package com.example.demo.utils;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.sql.Types;
//import java.util.Collections;
//
///*
//* mp代码生成器
//* */
//public class Generator {
//    public static void main(String[] args){
//        generate();
//    }
//
//    private static void generate(){
//        FastAutoGenerator.create("dbc:mysql://localhost:3306/login?serverTimezone=GMT%2b8", "root", "155786")
//                .globalConfig(builder -> {
//                    builder.author("qqy") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("F:\\2023春\\移动互联网\\PJ\\demo\\demo\\src\\main\\java\\"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.example.demo") // 设置父包名
//                            .moduleName("") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F:\\2023春\\移动互联网\\PJ\\demo\\demo\\src\\main\\java\\com\\example\\demo\\mapper\\")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("sys_user") // 设置需要生成的表名
//                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
//                })
////                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//}
