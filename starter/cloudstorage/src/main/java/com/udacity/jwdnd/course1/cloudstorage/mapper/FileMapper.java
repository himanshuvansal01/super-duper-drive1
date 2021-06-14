package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {

    @Select("SELECT filename FROM FILES WHERE userid = #{userID}")
    String[] getFileListings(Integer userID);

    @Insert("INSERT INTO FILES(filename, contenttype, filesize,userid,filedata)" +
    "VALUES(#{FileName}, #{ContentType}, #{FileSize}, #{UserID}, #{FileData})")
    @Options(useGeneratedKeys = true, keyProperty = "FileID")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFile(String fileName);

    @Delete("DELETE FROM FILES WHERE filename= #{fileName}")
    void deleteFile(String fileName);
}
