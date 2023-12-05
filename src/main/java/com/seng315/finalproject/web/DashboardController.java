package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.AddressRepository;
import com.seng315.finalproject.domain.UserInfoRepository;
import com.seng315.finalproject.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    AddressRepository addressRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private String getUsername() {
        Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user instanceof UserDetails) {
            return ((UserDetails)user).getUsername();
        }
        return "Anonymous";
    }

    @GetMapping(path = "/users-count")
    public String getUserTotal() {

        logger.info("User " + getUsername() + " used getUserTotal()") ;

        return userRepository.getUserCount().toString();
    }
    @GetMapping(path = "/admins-count")
    public String getAdminTotal() {

        logger.info("User " + getUsername() + " used getAdminTotal()") ;

        return userRepository.getAdminCount().toString();
    }

    @GetMapping(path = "/new-monthly-users-count")
    public String getNewMonthlyUsersCount() {

        logger.info("User " + getUsername() + " used getNewMonthlyUsersCount()") ;

        return userRepository.getNewUserCountMonthly().toString();
    }

    @GetMapping(path = "/new-trimonthly-users-count")
    public String getNewTriMonthlyUsersCount() {

        logger.info("User " + getUsername() + " used getNewTriMonthlyUsersCount()") ;

        return userRepository.getNewUserCountTriMonthly().toString();
    }

    @GetMapping(path = "/new-yearly-users-count")
    public String getNewYearlyUsersCount() {

        logger.info("User " + getUsername() + " used getNewYearlyUsersCount()") ;

        return userRepository.getNewUserCountYearly().toString();
    }
}
