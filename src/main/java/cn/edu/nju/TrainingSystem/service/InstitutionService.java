package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.entity.*;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
public interface InstitutionService {

    boolean login(String id, String password);

    boolean login(int id, String password);

    Institution get(int id);

    List<Institution> getList();

    boolean edit(Institution institution);

    boolean delete(int id);

    Integer register(Institution institution);

    List<Course> getAllCourse(int id);

    List<Course> getUnselectedCourse(int id, int sid);

    List<EnrollRecord> getEnrolled(int id);

    List<DropRecord> getDroped(int id);

    List<InstitutionPayment> getPayment(int id);

    List<InstitutionRefund> getRefund(int id);

    boolean uploadGrades(int courseId, int studentId, Double grades);

    List<StudentGradesVO> getStudentGrades(int institutionId);

}
