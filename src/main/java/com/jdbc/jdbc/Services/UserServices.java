package com.jdbc.jdbc.Services;

import com.jdbc.jdbc.model.User;
import com.jdbc.jdbc.model.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface UserServices {
    List<User> getUserData();
    String insertUser(Users user);
    String updateUser(int id, Users user);
    String deleteUser(int id);
    String uplodeImage(String path, MultipartFile file) throws IOException;
    InputStream getResource(String path, String Filename) throws FileNotFoundException;
}
