package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.DAO.InstitutionDAO;
import cn.edu.nju.TrainingSystem.entity.*;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Service("institutionService")
@Transactional
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionDAO institutionDAO;

    public boolean login(String id, String password) {
        return institutionDAO.login(id, password);
    }

    public boolean login(int id, String password) {
        return institutionDAO.login(id, password);
    }

    public Institution get(int id) {
        return institutionDAO.get(id);
    }

    public List<Institution> getList() {
        return institutionDAO.getList();
    }

    public boolean edit(Institution institution) {
        return institutionDAO.edit(institution);
    }

    public boolean delete(int id) {
        return institutionDAO.delete(id);
    }

    public Integer register(Institution institution) {
        return institutionDAO.register(institution);
    }

    public List<Course> getAllCourse(int id) {
        return institutionDAO.getAllCourse(id);
    }

    public List<Course> getUnselectedCourse(int id, int sid) {
        return institutionDAO.getUnselectedCourse(id, sid);
    }

    public List<EnrollRecord> getEnrolled(int id) {
        return institutionDAO.getEnrolled(id);
    }

    public List<DropRecord> getDroped(int id) {
        return institutionDAO.getDroped(id);
    }

    public List<InstitutionPayment> getPayment(int id) {
        return institutionDAO.getPayment(id);
    }

    public List<InstitutionRefund> getRefund(int id) {
        return institutionDAO.getRefund(id);
    }

    public boolean uploadGrades(int courseId, int studentId, Double grades) {
        return institutionDAO.uploadGrades(courseId, studentId, grades);
    }

    public List<StudentGradesVO> getStudentGrades(int institutionId) {
        return institutionDAO.getStudentGrades(institutionId);
    }
}
