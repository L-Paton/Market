package com.laura.spring.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.laura.spring.storage.FileResponse;
import com.laura.spring.storage.StorageService;

@Controller
public class FileUploadController {
	
	   private StorageService storageService;

	    public FileUploadController(StorageService storageService) {
	        this.storageService = storageService;
	    }

	    @GetMapping("/images/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

	        Resource resource = storageService.loadAsResource(filename);

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }

	    @PostMapping("/images/upload")
	    @ResponseBody
	    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	        String name = storageService.store(file);

	        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/images/")
	                .path(name)
	                .toUriString();

	        return new FileResponse(name, uri, file.getContentType(), file.getSize());
	    }
}
