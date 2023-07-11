package com.example.demo.controller.DTO;

import lombok.Data;

@Data
public class LibraryDTO {
    private String library_id;
    private String user_id;
    private String topic;
    private String desc;
    private String is_public;
}
