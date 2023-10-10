package com.jdbc.jdbc.dao;

import com.jdbc.jdbc.model.User;
import com.jdbc.jdbc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
}
