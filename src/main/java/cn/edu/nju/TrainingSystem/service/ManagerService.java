package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.entity.AddCourseRequest;
import cn.edu.nju.TrainingSystem.entity.EditCourseRequest;
import cn.edu.nju.TrainingSystem.entity.StudentPayment;
import cn.edu.nju.TrainingSystem.entity.StudentRefund;
import cn.edu.nju.TrainingSystem.vo.StudentCountVO;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
public interface ManagerService {

    boolean approveAddRequest(String[] array);

    boolean approveEditRequest(String[] array);

    List<AddCourseRequest> getAddRequest();

    List<EditCourseRequest> getEditRequest();

    boolean approvePayment(String[] array);

    boolean approveRefund(String[] array);

    List<StudentPayment> getUnfinishedPayment();

    List<StudentRefund> getUnfinishedRefund();

    List<StudentCountVO> getStudentCount();

    List<StudentGradesVO> getStudentGrades();

}
