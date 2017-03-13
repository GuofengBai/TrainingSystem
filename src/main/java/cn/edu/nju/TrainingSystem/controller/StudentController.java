package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.entity.Student;
import cn.edu.nju.TrainingSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "studentRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String name, String password, String bankcard) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(password);
        student.setBankCard(bankcard);
        studentService.register(student);
        return "ha ha ha!!!!";
    }
}
