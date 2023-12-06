package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.user.User;
import com.seng315.finalproject.domain.user.UserRepository;
import com.seng315.finalproject.security.AccountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/list")
    public Iterable<User> getAllUsers() {

        logger.info("User " + AccountUtil.getUsername() + " used getAllUsers().") ;

        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable int id) {

        logger.info("User " + AccountUtil.getUsername() + " used getUser().") ;

        return userRepository.findById(id);
    }

    @PutMapping
    public String updateUser(@RequestBody Map<String,String> formData) {
        Optional<User> userOptional = userRepository.findById(Integer.parseInt(formData.get("userID")));

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserID(Integer.parseInt(formData.get("userID")));
            user.setUserInfoID(Integer.parseInt(formData.get("userInfoID")));
            user.setAddressID(Integer.parseInt(formData.get("addressID")));
            user.setUsername(formData.get("username"));
            user.setRole(formData.get("role"));
            user.setActive(Boolean.parseBoolean(formData.get("active")));
            userRepository.save(user);
            logger.info("User " + AccountUtil.getUsername() + " updated " + user.getUsername() + ".");
            return "User " + user.getUsername() + " updated.";
        }
        return "User not found.";
    }

    @PostMapping
    public String addUser(@RequestBody Map<String,String>  formData) {

        User user = new User();
        Date date = new java.util.Date();

       if(userRepository.findByUsername(formData.get("username")) == null) {

            user.setUserInfoID(Integer.parseInt(formData.get("userInfoID")));
            user.setAddressID(Integer.parseInt(formData.get("addressID")));
            user.setUsername(formData.get("username"));
            user.setPassword(passwordEncoder.encode(formData.get("password")));
            user.setRole(formData.get("role"));
            user.setActive(true);
            user.setCreatedAt(new Date(date.getTime()));
            userRepository.save(user);
            logger.info("User " + AccountUtil.getUsername() + " added " + user.getUsername() + " to database.");
            return "User " + user.getUsername() + " added to database.";
        }
        return "User already exists.";
    }


}
