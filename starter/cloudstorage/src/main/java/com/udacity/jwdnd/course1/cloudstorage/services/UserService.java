package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user){
        SecureRandom Random = new SecureRandom();
        byte[] Salt = new byte[16];
        Random.nextBytes(Salt);
        String EncodedSalt = Base64.getEncoder().encodeToString(Salt);
        String HashedPassword = hashService.getHashedValue(user.getPassword(),EncodedSalt);
        return userMapper.insert(new User(null, user.getUsername(),EncodedSalt,HashedPassword, user.getFirstName(), user.getLastName()));

    }

    public boolean isUserNameAvailable(String UserName){
        return userMapper.getUser(UserName) == null;
    }

    public User getUser(String UserName){
        return userMapper.getUser(UserName);
    }

    public User getUser(Integer UserID){
        return userMapper.getUserID(UserID);
    }
}
