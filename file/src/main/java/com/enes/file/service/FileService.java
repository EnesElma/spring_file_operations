package com.enes.file.service;

import com.enes.file.entity.FileInfo;
import com.enes.file.repo.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FileService implements IFileService{

    @Value("${file.upload.dir}")
    private String FILE_DIR;

    @Autowired
    private IFileRepository fileRepository;

    private final List<String> fileTypes = Arrays.asList("application/pdf", "image/png", "image/jpeg", "image/jpg",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",      //docx file contenttype
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");           //xlsx file contenttype

    @Override
    public FileInfo uploadFile(MultipartFile file, long userId) throws IOException {
        if (fileTypes.contains(file.getContentType())){
            File directory = new File(FILE_DIR+userId);
            if (!directory.exists()){
                directory.mkdir();
            }

            File file1 = new File(FILE_DIR+userId+"\\"+file.getOriginalFilename());
            file1.createNewFile();
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(file.getBytes());
            fos.close();

            return saveFileInfo(file.getOriginalFilename(),userId,file1);
        }
        else return null;
    }

    @Override
    public List<FileInfo> listFiles(){
        return fileRepository.findAll();
    }

    @Override
    public Optional<FileInfo> findFile(long id){
        return fileRepository.findById(id);
    }

    @Override
    public void deleteFile(long id){
        FileInfo fileInfo = fileRepository.findById(id).get();
        File file = new File(FILE_DIR+fileInfo.getUserId()+"\\"+fileInfo.getFilename());
        file.delete();
        fileRepository.deleteById(id);
    }
    private FileInfo saveFileInfo(String filename,long userId,File file){
        int lastIndexOf = filename.lastIndexOf(".");
        String extension = filename.substring(lastIndexOf+1);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilename(filename);
        fileInfo.setFiletype(extension);
        fileInfo.setPath(FILE_DIR+userId+"\\"+filename);
        fileInfo.setUserId(userId);
        fileInfo.setFileSizeKB(file.length()/1024);
        return fileRepository.save(fileInfo);
    }
}
