package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.entity.Course;
import cn.edu.nju.TrainingSystem.entity.Institution;
import cn.edu.nju.TrainingSystem.service.CourseService;
import cn.edu.nju.TrainingSystem.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("institutionId");
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "institutionRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(String name, String password, String bankcard, HttpServletResponse response) {
        Institution institution = new Institution();
        institution.setName(name);
        institution.setPassword(password);
        institution.setBankcard(bankcard);
        int id = institutionService.register(institution);
        AlertInformation.generateAlert(response, "注册成功！您的机构id是" + id + ",请务必牢记此id。");
    }

    @RequestMapping("/home")
    public String homePage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("institutionId").toString());
        Institution institution = institutionService.get(id);
        model.addAttribute("institution", institution);
        return "institutionHome";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("institutionId").toString());
        Institution institution = institutionService.get(id);
        model.addAttribute("institution", institution);
        return "institutionEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(String name, String bankcard, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("institutionId").toString());
        Institution institution = institutionService.get(id);
        institution.setName(name);
        institution.setBankcard(bankcard);
        institutionService.edit(institution);
        return "redirect:/institution/edit";
    }

    @RequestMapping("/analysis")
    public String analysisPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("institutionId").toString());
        model.addAttribute("enrolled", institutionService.getEnrolled(id));
        model.addAttribute("droped", institutionService.getDroped(id));
        model.addAttribute("payment", institutionService.getPayment(id));
        model.addAttribute("refund", institutionService.getRefund(id));
        return "institutionAnalysis";
    }

    @RequestMapping("/course")
    public String coursePage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("institutionId").toString());
        model.addAttribute("course", institutionService.getAllCourse(id));
        return "institutionCourse";
    }

    @RequestMapping("/course/{courseId}")
    public String coursePage(Model model, @PathVariable("courseId") String courseId) {
        int id = Integer.parseInt(courseId);
        Course course = courseService.find(id);
        model.addAttribute("course", course);
        model.addAttribute("grades", courseService.getStudentGrades(id));
        return "courseDetail";
    }

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    public void uploadGrades(String courseId, String studentId, String grades, HttpServletResponse response) {
        institutionService.uploadGrades(Integer.parseInt(courseId), Integer.parseInt(studentId), Double.parseDouble(grades));
        AlertInformation.generateAlert(response, "录入成功");
    }
}
