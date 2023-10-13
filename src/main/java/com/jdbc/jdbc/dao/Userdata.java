package com.jdbc.jdbc.dao;

import com.jdbc.jdbc.model.User;
import com.jdbc.jdbc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class Userdata {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Userdata(){
    }

    public  int CreateTable(){
        String query = "CREATE TABLE IF NOT EXISTS USERDATA (id INT NOT NULL AUTO_INCREMENT, email VARCHAR(45) NULL, pass VARCHAR(45) NULL, PRIMARY KEY (id),UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE)";
        int Update = this.jdbcTemplate.update(query);
        return Update;
    }

    public List<User> getUserData(){
        String sql = "SELECT id, email, pass FROM userdata";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User userData = new User();
            userData.setId(rs.getInt("id"));
            userData.setEmail(rs.getString("email"));
            userData.setPass(rs.getString("pass"));
            return userData;
        });
    }

    public String insertUser(Users user) {
        String query = "INSERT INTO userdata (email, pass) VALUES (?, ?)";
        jdbcTemplate.update(query, user.email   , user.pass);
        return "User inserted successfully";
    }

    public String updateUser(int id, Users user) {
        String query = "UPDATE userdata SET email = ?, pass = ? WHERE id = ?";
        jdbcTemplate.update(query, user.email, user.pass, id);
        return "User updated successfully";
    }

    public String deleteUser(int id) {
        String query = "DELETE FROM userdata WHERE id = ?";
        jdbcTemplate.update(query, id);
        return "User deleted successfully";
    }

    public String uplodeImage(String path, MultipartFile file) throws IOException {
        //File Name
        String name = file.getOriginalFilename();


        String rendomId = UUID.randomUUID().toString();
        String FileRandomName = rendomId.concat(name.substring(name.lastIndexOf(".")));

        //Full Path
        String FullPath = path + File.separator + FileRandomName;

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(FullPath));
        return FileRandomName;
    }


    public InputStream getResource(String path, String Filename) throws FileNotFoundException {
        String FullPath = path + File.separator +Filename;
        InputStream is = new FileInputStream(FullPath);
        return is;
    }
}
