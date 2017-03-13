package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.service.InstitutionService;
import cn.edu.nju.TrainingSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by baiguofeng on 2017/3/8.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String id, String password, String type, HttpServletRequest request) {
        if (type.equals("manager")) {
            return "manager";
        } else if (type.equals("student")) {
            if (studentService.login(id, password)) {
                request.getSession().setAttribute("studentId", Integer.parseInt(id));
                return "redirect:/student/home";
            } else {
                return "warning";
            }
        } else {
            if (institutionService.login(id, password)) {
                request.getSession().setAttribute("institutionId", Integer.parseInt(id));
                return "redirect:/institution/home";
            } else {
                return "warning";
            }
        }
    }

}
