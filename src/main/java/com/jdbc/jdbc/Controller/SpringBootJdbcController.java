package com.jdbc.jdbc.Controller;

import com.jdbc.jdbc.dao.Userdata;
import com.jdbc.jdbc.model.User;
import com.jdbc.jdbc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ComponentScan(basePackages = "com.jdbc.jdbc.dao.Userdata")
@Configuration
@RestController
@RequestMapping("api")
public class SpringBootJdbcController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Userdata userdata;

    @Autowired
    public SpringBootJdbcController(Userdata userdata) {
        this.userdata = userdata;
    }

    @GetMapping("/hello")
    public String index(){
        return"Hello World";
    }

    @GetMapping("/user-list")
    public List<User> getAllUserList() {
        return userdata.getUserData();
    }
/*
    @PostMapping("/insert-user")
    public String insertUser(@RequestParam String email, @RequestParam String pass) {
        String query = "INSERT INTO userdata (email, pass) VALUES (?, ?)";
        jdbcTemplate.update(query, email, pass);
        return "User inserted successfully";
    }*/

    @PostMapping("/insert-user")
    public String insertUser(@RequestBody Users user) {
        return  userdata.insertUser(user);
    }

    @PutMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody Users user) {
        return  userdata.updateUser(id, user);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return  userdata.deleteUser(id);
    }
}
