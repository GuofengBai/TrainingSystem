package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/12.
 */
public interface CourseDAO {

    List<Course> getList();

    List<Course> getUnselectedList(int studentId);

    Course find(int id);

    boolean selectCourse(List<EnrollRecord> enrollRecordList);

    boolean dropCourse(List<DropRecord> dropRecordList);

    boolean addRequest(AddCourseRequest addCourseRequest);

    boolean editRequest(EditCourseRequest editCourseRequest);

    List<StudentGradesVO> getStudentGrades(int courseId);

}
