package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.entity.User;
import cn.edu.nju.TrainingSystem.service.StudentService;
import cn.edu.nju.TrainingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiguofeng on 2017/3/8.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("")
    public String home() {
        List<User> us = new ArrayList<User>();
        User u = new User();
        u.setName("axy14");
        us.add(u);
        u = new User();
        u.setName("cjh14");
        us.add(u);
        userService.saveUsers(us);
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<User> json() {
        return userService.getAllUsernames();
    }

}
