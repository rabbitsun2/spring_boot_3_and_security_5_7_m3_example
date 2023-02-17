package com.cakeon.springboot;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.cakeon.springboot.dto.FileDto;
import com.cakeon.springboot.util.FileStore;
import com.cakeon.springboot.util.UploadFile;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MyController {

	@Autowired
	private FileStore fileStore;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "FileUpload";
	}
	
	@RequestMapping("/uploadForm")
	public String uploadForm() {
		return "fileForm";
	}
	
	@RequestMapping("/uploadOk")
	public String uploadOk(FileDto form) throws IOException{
		
		// 첨부파일, 이미지들 처리하는 부분
	    UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
	    List<UploadFile> imageFiles = fileStore.storeFiles(form.getImageFiles());
		
		return "fileForm_ok";
	}
	
	// 이미지 보기
	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource showImage(@PathVariable String filename) throws MalformedURLException {
		String realPath = filename + ".jpg";
	    return new UrlResource("file:" + fileStore.getFullPath(realPath));
	}

	// 다운로드 기능
	@GetMapping("/attach/{uuid}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable String uuid) throws MalformedURLException {
//	    Content content = contentService.getContent(id);
//
//	    System.out.println(content.getAttachFile());
//	    String storeFilename = content.getAttachFile().getStoreFilename();
//	    String uploadFilename = content.getAttachFile().getUploadFilename();
//	    System.out.println(fileStore.getFullPath(storeFilename));

		String tmpFilename = uuid + ".jpg";
		String tmpRealFilename = "13a99263-6a7c-4814-a3fd-60a3e6a48b58_한글.jpg";
		
	    UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(tmpFilename));

	    // 업로드 한 파일명이 한글인 경우 아래 작업을 안해주면 한글이 깨질 수 있음
	    String encodedUploadFileName = UriUtils.encode(tmpRealFilename, StandardCharsets.UTF_8);
	    String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

	    // header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
	            .body(urlResource);
	}
			
}
