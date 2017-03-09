package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/8.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<String> json(){
        return userService.getAllUsernames();
    }
}
