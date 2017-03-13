package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.service.CourseService;
import cn.edu.nju.TrainingSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String enrollPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        model.addAttribute("toSelect", courseService.getUnselectedList(id));
        return "enrollCourse";
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> enroll(String array, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        String[] toSelect = array.split(",");
        courseService.selectCourse(toSelect, id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "选课成功，尽早快缴费");
        return map;
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public String dropPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        model.addAttribute("toDrop", studentService.getEnrolled(id));
        return "dropCourse";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> drop(String array, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        String[] toDrop = array.split(",");
        courseService.dropCourse(toDrop, id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "退课成功，我们会退还课程价格一半的金额到您的余额，请注意查看！");
        return map;
    }
}
