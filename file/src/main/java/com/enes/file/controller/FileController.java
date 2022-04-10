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
import java.util.Optional;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping(value = "upload",consumes = {"multipart/form-data"})
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("userId") long userId) throws IOException {
        FileInfo fileInfo = fileService.uploadFile(file,userId);

        if (fileInfo != null)   return new ResponseEntity<>(fileInfo, HttpStatus.CREATED);
        else {
            Map<String, String> message = new HashMap<>();
            message.put("message", "Wrong file type. Required types (png,jpeg,jpg,docx,pdf,xlsx)");
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "update",consumes = {"multipart/form-data"})
    public ResponseEntity<?> fileUpdate(@RequestParam("file") MultipartFile file, @RequestParam("fileId") long id) throws IOException {
        FileInfo fileInfo = fileService.updateFile(file,id);

        if (fileInfo != null)   return new ResponseEntity<>(fileInfo, HttpStatus.ACCEPTED);
        else {
            Map<String, String> message = new HashMap<>();
            message.put("message", "Wrong file type. Required types (png,jpeg,jpg,docx,pdf,xlsx)");
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("listAll")
    public ResponseEntity<?> listFiles(){
        return ResponseEntity.ok(fileService.listFiles());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findFile(@PathVariable long id){
        Optional<FileInfo> fileInfo = fileService.findFile(id);

        if (fileInfo.isPresent()) return new ResponseEntity<>(fileInfo, HttpStatus.OK);
        else {
            Map<String, String> message = new HashMap<>();
            message.put("message", "File not found");
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        fileService.deleteFile(id);
        return new ResponseEntity<>("File delete success",HttpStatus.OK);
    }
}
