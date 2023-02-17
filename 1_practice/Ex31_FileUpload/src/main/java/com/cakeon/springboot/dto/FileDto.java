package com.cakeon.springboot.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDto {
	
	private int id;
    private String title;
    private String texts;

    private String writer;
    private String password;

    private String updateDate;

    private MultipartFile attachFile;          		// 첨부 파일
    private List<MultipartFile> imageFiles;    // 첨부 이미지
    
}
