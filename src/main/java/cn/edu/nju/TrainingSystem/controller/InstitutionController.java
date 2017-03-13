package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.entity.Institution;
import cn.edu.nju.TrainingSystem.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Controller
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

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
}
