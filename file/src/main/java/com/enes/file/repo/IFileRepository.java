package com.enes.file.repo;

import com.enes.file.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepository extends JpaRepository<FileInfo,Long> {
}
