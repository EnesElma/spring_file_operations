package com.enes.file.controller;

import com.enes.file.entity.FileInfo;
import com.enes.file.service.IFileService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping(value = "upload",consumes = {"multipart/form-data"})
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("userId") long userId) throws IOException {
        FileInfo fileInfo = fileService.uploadFile(file,userId);

        if (fileInfo != null)   return new ResponseEntity<>(fileInfo, HttpStatus.OK);
        else {
            Map<String, String> message = new HashMap<>();
            message.put("message", "Wrong file type. Required types (png,jpeg,jpg,docx,pdf,xlsx)");
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

}
