package com.enes.file.service;

import com.enes.file.entity.FileInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IFileService {
    FileInfo uploadFile(MultipartFile file, long userId) throws IOException;

    List<FileInfo> listFiles();

    Optional<FileInfo> findFile(long id);

    void deleteFile(long id);
}
