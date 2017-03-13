package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.entity.Student;
import cn.edu.nju.TrainingSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public void register(String name, String password, String bankcard, HttpServletResponse response) {
        Student student = new Student();
        student.setName(name);
        student.setPassword(password);
        student.setBankCard(bankcard);
        int id = studentService.register(student);
        AlertInformation.generateAlert(response, "注册成功！您的学生id是" + id + ",请务必牢记此id。");
    }

    @RequestMapping("/home")
    public String homePage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        Student student = studentService.find(id);
        model.addAttribute("student", student);
        return "studentHome";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        Student student = studentService.find(id);
        model.addAttribute("student", student);
        return "studentEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Model model, String name, String bankcard, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        Student student = studentService.find(id);
        student.setName(name);
        student.setBankCard(bankcard);
        studentService.edit(student);
        return "redirect:/student/edit";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    public String chargePage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        Student student = studentService.find(id);
        model.addAttribute("student", student);
        return "studentCharge";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public String charge(String amount, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        studentService.charge(id, Double.parseDouble(amount));
        return "redirect:/student/charge";
    }

    @RequestMapping("/analysis")
    public String analysisPage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        model.addAttribute("enrolled", studentService.getEnrolled(id));
        model.addAttribute("droped", studentService.getDroped(id));
        model.addAttribute("payment", studentService.getExpense(id));
        model.addAttribute("refund", studentService.getRefend(id));
        return "studentAnalysis";
    }

    @RequestMapping(value = "/consume", method = RequestMethod.GET)
    public String consumePage(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getSession().getAttribute("studentId").toString());
        model.addAttribute("toConsume", studentService.getUnfinishedExpense(id));
        return "studentConsume";
    }

    @RequestMapping(value = "/consume", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> consume(String array) {
        String[] toConsume = array.split(",");
        studentService.consume(toConsume);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "支付成功，您已可以开始上课！");
        return map;
    }
}
