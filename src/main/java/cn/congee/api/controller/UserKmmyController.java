package cn.congee.api.controller;

import cn.congee.api.common.OutputObject;
import cn.congee.api.common.ResultObj;
import cn.congee.api.entity.UserKmmy;
import cn.congee.api.service.UserKmmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yang
 * @Date: 2020-12-10 7:27
 */
@RestController
@RequestMapping("/userKmmy")
public class UserKmmyController {

    @Autowired
    private UserKmmyService userKmmyService;

    @PostMapping("/login")
    public OutputObject login(@RequestBody UserKmmy userKmmy){
        return userKmmyService.login(userKmmy);
    }

    @PostMapping("/addUser")
    public ResultObj addUser(@RequestBody UserKmmy userKmmy){
        return userKmmyService.addUser(userKmmy);
    }


}
