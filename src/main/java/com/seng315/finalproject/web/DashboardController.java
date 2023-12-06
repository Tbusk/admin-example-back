package com.seng315.finalproject.web;

import com.seng315.finalproject.domain.address.AddressRepository;
import com.seng315.finalproject.domain.userinfo.UserInfoRepository;
import com.seng315.finalproject.domain.user.UserRepository;
import com.seng315.finalproject.security.AccountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "false")
@RequestMapping("/api/dashboard")
public class DashboardController {
    private static final Map<String, Integer> monthMap = new HashMap<>();

    static {
        monthMap.put("jan", 1);
        monthMap.put("feb", 2);
        monthMap.put("mar", 3);
        monthMap.put("apr", 4);
        monthMap.put("may", 5);
        monthMap.put("jun", 6);
        monthMap.put("jul", 7);
        monthMap.put("aug", 8);
        monthMap.put("sep", 9);
        monthMap.put("oct", 10);
        monthMap.put("nov", 11);
        monthMap.put("dec", 12);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    AddressRepository addressRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(path = "/new-users/users/count")
    public String getUserTotal() {

        logger.info("User " + AccountUtil.getUsername() + " used getUserTotal()") ;

        return userRepository.getUserCount().toString();
    }
    @GetMapping(path = "/new-users/admins/count")
    public String getAdminTotal() {

        logger.info("User " + AccountUtil.getUsername() + " used getAdminTotal()") ;

        return userRepository.getAdminCount().toString();
    }

    @GetMapping(path = "/new-users/pastmonth/count")
    public String getNewMonthlyUsersCount() {

        logger.info("User " + AccountUtil.getUsername() + " used getNewMonthlyUsersCount()") ;

        return userRepository.getNewUserCountMonthly().toString();
    }

    @GetMapping(path = "/new-users/trimonthly/count")
    public String getNewTriMonthlyUsersCount() {

        logger.info("User " + AccountUtil.getUsername() + " used getNewTriMonthlyUsersCount()") ;

        return userRepository.getNewUserCountTriMonthly().toString();
    }

    @GetMapping(path = "/new-users/yearly/count")
    public String getNewYearlyUsersCount() {

        logger.info("User " + AccountUtil.getUsername() + " used getNewYearlyUsersCount()") ;

        return userRepository.getNewUserCountYearly().toString();
    }

    @GetMapping(path = "/new-users/{year}/{month}")
    public String getNewUsersByMonth(@PathVariable("year") Integer year, @PathVariable("month") String month) {

        logger.info("User " + AccountUtil.getUsername() + " used getNewUsersByMonth()") ;

        System.out.println("year: " + year + ", month: " + monthMap.get(month) );
        return userRepository.getNewUserByMonth(year, monthMap.get(month)).toString();
    }
}
