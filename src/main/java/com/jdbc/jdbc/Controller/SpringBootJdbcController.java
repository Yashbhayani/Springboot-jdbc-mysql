package com.jdbc.jdbc.Controller;

import com.jdbc.jdbc.Services.UserServices;
import com.jdbc.jdbc.dao.Userdata;
import com.jdbc.jdbc.model.FileResponse;
import com.jdbc.jdbc.model.User;
import com.jdbc.jdbc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ComponentScan(basePackages = "com.jdbc.jdbc.dao.Userdata")
@Configuration
@RestController
@RequestMapping("api")
public class SpringBootJdbcController {
    @Autowired
    private final UserServices userServices;
    @Value("${project.image}")
    private  String path;

    public SpringBootJdbcController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/hello")
    public String index(){
        return"Hello World";
    }

    @GetMapping("/user-list")
    public List<User> getAllUserList() {
        return userServices.getUserData();
    }

    @PostMapping("/insert-user")
    public String insertUser(@RequestBody Users user) {
        return  userServices.insertUser(user);
    }

    @PutMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody Users user) {
        return  userServices.updateUser(id, user);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return  userServices.deleteUser(id);
    }

    @PostMapping("/image")
    public ResponseEntity<FileResponse> image(@RequestParam("images") MultipartFile images){
        String FileName = null;
        try {
            FileName = userServices.uplodeImage(path, images);
        } catch (IOException e) {
            return new ResponseEntity<>(new FileResponse(null, "image is not Uploding"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(FileName, "image Uploding"), HttpStatus.OK);
    }

@GetMapping(value = "/images/{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
public ResponseEntity<byte[]> showImage(@PathVariable("imageName") String imageName) throws IOException {
    InputStream resource = userServices.getResource(path, imageName);

    byte[] imageData = StreamUtils.copyToByteArray(resource);

    return new ResponseEntity<>(imageData, HttpStatus.OK);
}


    @PostMapping("/img")
    public String img(@RequestParam("images") MultipartFile images){
        String FileName = null;
        try {
            FileName = userServices.uplodeImage(path, images);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileName;
    }


}
