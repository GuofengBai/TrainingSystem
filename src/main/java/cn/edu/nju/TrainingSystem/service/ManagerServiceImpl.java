package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.DAO.ManagerDAO;
import cn.edu.nju.TrainingSystem.entity.AddCourseRequest;
import cn.edu.nju.TrainingSystem.entity.EditCourseRequest;
import cn.edu.nju.TrainingSystem.entity.StudentPayment;
import cn.edu.nju.TrainingSystem.entity.StudentRefund;
import cn.edu.nju.TrainingSystem.vo.StudentCountVO;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDAO managerDAO;

    public boolean approveAddRequest(String[] array) {
        return managerDAO.approveAddRequest(array);
    }

    public boolean approveEditRequest(String[] array) {
        return managerDAO.approveEditRequest(array);
    }

    public List<AddCourseRequest> getAddRequest() {
        return managerDAO.getAddRequest();
    }

    public List<EditCourseRequest> getEditRequest() {
        return managerDAO.getEditRequest();
    }

    public boolean approvePayment(String[] array) {
        return managerDAO.approvePayment(array);
    }

    public boolean approveRefund(String[] array) {
        return managerDAO.approveRefund(array);
    }

    public List<StudentPayment> getUnfinishedPayment() {
        return managerDAO.getUnfinishedPayment();
    }

    public List<StudentRefund> getUnfinishedRefund() {
        return managerDAO.getUnfinishedRefund();
    }

    public List<StudentCountVO> getStudentCount() {
        return managerDAO.getStudentCount();
    }

    public List<StudentGradesVO> getStudentGrades() {
        return managerDAO.getStudentGrades();
    }
}
