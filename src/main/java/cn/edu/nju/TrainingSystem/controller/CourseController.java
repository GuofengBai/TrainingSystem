package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.entity.AddCourseRequest;
import cn.edu.nju.TrainingSystem.entity.EditCourseRequest;
import cn.edu.nju.TrainingSystem.service.CourseService;
import cn.edu.nju.TrainingSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

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
    public String enroll(String array, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        String[] toSelect = array.split(",");
        courseService.selectCourse(toSelect, id);
        return "选课成功，尽早快缴费";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.GET)
    public String dropPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        model.addAttribute("toDrop", studentService.getEnrolled(id));
        return "dropCourse";
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public String drop(String array, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        String[] toDrop = array.split(",");
        courseService.dropCourse(toDrop, id);
        return "退课成功，我们会退还课程价格一半的金额到您的余额，请注意查看！";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("institutionId").toString());
        model.addAttribute("institutionId", id);
        return "addCourse";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(String institutionId, String name, String teacher, Date startDate,
                    Date endDate, String price, HttpServletResponse response) {
        AddCourseRequest addCourseRequest = new AddCourseRequest();
        System.out.println(institutionId);
        addCourseRequest.setInstitutionId(Integer.parseInt(institutionId));
        addCourseRequest.setName(name);
        addCourseRequest.setTeacher(teacher);
        addCourseRequest.setStartTime(startDate);
        addCourseRequest.setEndTime(endDate);
        addCourseRequest.setPrice(Double.parseDouble(price));
        courseService.addRequest(addCourseRequest);
        AlertInformation.generateAlert(response, "开课成功！");
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public void edit(String courseId, String institutionId, String name, String teacher,
                     Date startDate, Date endDate, String price, HttpServletResponse response) {
        EditCourseRequest editCourseRequest = new EditCourseRequest();
        editCourseRequest.setCourseId(Integer.parseInt(courseId));
        editCourseRequest.setInstitutionId(Integer.parseInt(institutionId));
        editCourseRequest.setName(name);
        editCourseRequest.setTeacher(teacher);
        editCourseRequest.setStartTime(startDate);
        editCourseRequest.setEndTime(endDate);
        editCourseRequest.setPrice(Double.parseDouble(price));
        courseService.editRequest(editCourseRequest);
        AlertInformation.generateAlert(response, "修改成功！");
    }
}
