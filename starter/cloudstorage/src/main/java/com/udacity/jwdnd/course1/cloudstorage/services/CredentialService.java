package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    public  void AddCredential(String URL, String UserName, String CredentialUserName, String Key, String Password ){
        Integer UserID = userMapper.getUser(UserName).getUserID();
        Credential credential = new Credential(0, URL, CredentialUserName,Key,Password,UserID);
        credentialMapper.insert(credential);
    }

    public Credential[] getCredentialListings(Integer UserID){
        return credentialMapper.getCredentialListings(UserID);
    }

    public Credential getCredential(Integer NoteID){
        return credentialMapper.getCredential(NoteID);
    }

    public void deleteCredential(Integer NoteID){
        credentialMapper.deleteCredential(NoteID);
    }

    public void updateCredential(Integer CredentialID, String UserName, String URL,String Key, String Password){
        credentialMapper.updateCredential(CredentialID, UserName,URL,Key,Password);


    }
}
