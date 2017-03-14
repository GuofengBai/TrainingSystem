package cn.edu.nju.TrainingSystem.controller;

import cn.edu.nju.TrainingSystem.service.InstitutionService;
import cn.edu.nju.TrainingSystem.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private InstitutionService institutionService;

    @RequestMapping("/analysis")
    public String analysisPage(Model model) {
        model.addAttribute("scount", managerService.getStudentCount());
        model.addAttribute("sgrades", managerService.getStudentGrades());
        return "managerAnalysis";
    }

    @RequestMapping("/finance")
    public String financePage(Model model) {
        model.addAttribute("ilist", institutionService.getList());
        return "managerFinance";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        model.addAttribute("addList", managerService.getAddRequest());
        return "managerAddCourse";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(String array) {
        String[] toAdd = array.split(",");
        managerService.approveAddRequest(toAdd);
        return "批准开课，新的课程将被加入！";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(Model model) {
        model.addAttribute("editList", managerService.getEditRequest());
        return "managerEditCourse";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(String array) {
        String[] toEdit = array.split(",");
        managerService.approveEditRequest(toEdit);
        return "批准修改课程，课程信息将被修改！";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String paymentPage(Model model) {
        model.addAttribute("payment", managerService.getUnfinishedPayment());
        return "managerPayment";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseBody
    public String payment(String array) {
        String[] plist = array.split(",");
        managerService.approvePayment(plist);
        return "结算成功，机构将马上收到相应钱款!";
    }

    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    public String refundPage(Model model) {
        model.addAttribute("refund", managerService.getUnfinishedRefund());
        return "managerRefund";
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ResponseBody
    public String refund(String array) {
        String[] rlist = array.split(",");
        managerService.approveRefund(rlist);
        return "退款批准，机构将被扣除相应余额，打入学生余额！";
    }
}
