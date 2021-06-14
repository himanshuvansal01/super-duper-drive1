package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {


    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
   Credential[] getCredentialListings(Integer userId);

    @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userid)" +
    "VALUES(#{URL}, #{UserName}, #{Key}, #{Password}, #{UserId})")
    @Options(useGeneratedKeys = true, keyProperty = "CredentialID")
   int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{CredentialID}")
    Credential getCredential(Integer CredentialID);


    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{CredentialID}")
     void deleteCredential(Integer CredentialID);


    @Update("UPDATE CREDENTIALS SET url = #{URL}, Key = #{Key}, password = #{password}, username = #{userName} WHERE credentialid = #{CredentialID}")
    void updateCredential(Integer CredentialID, String userName, String URL, String Key, String password);

}
