
package com.file_management.controller;

import com.file_management.models.File;
import com.file_management.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    FileRepository fileRepository;

    @GetMapping("/files")
    public List<File> getListFiles() {
        return fileRepository.findAll();
    }
}
