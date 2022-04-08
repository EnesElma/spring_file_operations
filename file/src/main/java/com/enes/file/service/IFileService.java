package com.enes.file.service;

import com.enes.file.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    FileInfo uploadFile(MultipartFile file, long userId) throws IOException;
}
