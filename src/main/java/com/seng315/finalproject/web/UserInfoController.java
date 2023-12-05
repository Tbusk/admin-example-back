package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.UserInfo;
import com.seng315.finalproject.domain.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/api/userinfo")
public class UserInfoController {

    @Autowired
    UserInfoRepository userInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private String getUsername() {
        Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails) {
            return ((UserDetails)user).getUsername();
        }
        return "Anonymous";
    }


    @GetMapping(path = "/list")
    public Iterable<UserInfo> getAllUserInfo() {

        logger.info("User " + getUsername() + " used getAllUsersInfo().") ;

        return userInfoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<UserInfo> getUserInfo(@PathVariable int id) {

        logger.info("User " + getUsername() + " used getUserInfo().") ;

        return userInfoRepository.findById(id);
    }

    @PutMapping
    public String updateUserInfo(@RequestBody Map<String,String> formData) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(Integer.parseInt(formData.get("userInfoID")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        if(userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();
            userInfo.setEmailAddress(formData.get("emailAddress"));
            userInfo.setFirstName(formData.get("firstName"));
            userInfo.setLastName(formData.get("lastName"));
            try {
                userInfo.setDateOfBirth((simpleDateFormat.parse(formData.get("dateOfBirth"))));
            } catch (Exception e) {System.out.println("Error in converting date of birth: " + e.getMessage());}
            userInfo.setPhoneNumber(formData.get("phoneNumber"));
            userInfoRepository.save(userInfo);
            logger.info("User " + getUsername() + " updated UserInfoID " + userInfo.getUserInfoID() + ".");
            return "UserInfoID " + userInfo.getUserInfoID() + " updated.";
        }
        return "Issue with updating UserInfo.";
    }

    @PostMapping
    public String addUserInfo(@RequestBody Map<String,String> formData) {

        UserInfo userInfo = new UserInfo();

        userInfo.setEmailAddress(formData.get("emailAddress"));
        userInfo.setFirstName(formData.get("firstName"));
        userInfo.setLastName(formData.get("lastName"));
        try {
            userInfo.setDateOfBirth(new Date(formData.get("dateOfBirth")));
        } catch (Exception e) {
            System.out.println("Error in converting date specified to date of birth value: " + e.getMessage());
        }
        userInfo.setPhoneNumber(formData.get("phoneNumber"));
        userInfoRepository.save(userInfo);
        logger.info("User " + getUsername() + " added users info to db. ");
        return "New UserInfo created.";
    }
}
