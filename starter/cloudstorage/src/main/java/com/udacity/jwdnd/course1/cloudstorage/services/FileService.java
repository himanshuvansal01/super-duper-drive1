package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public String[] getFileListings(Integer userID) {
        return fileMapper.getFileListings(userID);
    }

    public void addFile(MultipartFile multipartFile, String userName) throws IOException {

        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = userMapper.getUser(userName).getUserID();
        File file = new File(0, fileName,contentType,fileSize,userId, multipartFile.getBytes());
        fileMapper.insert(file);
    }

    public void deleteFile(String fileName) {

        fileMapper.deleteFile(fileName);
    }

    public File getFile(String fileName) {

        return fileMapper.getFile(fileName);
    }
}
