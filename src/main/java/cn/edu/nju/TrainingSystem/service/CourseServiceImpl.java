package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.DAO.CourseDAO;
import cn.edu.nju.TrainingSystem.entity.*;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;

    public List<Course> getList() {
        return courseDAO.getList();
    }

    public List<Course> getUnselectedList(int studentId) {
        return courseDAO.getUnselectedList(studentId);
    }

    public Course find(int id) {
        return courseDAO.find(id);
    }

    public boolean selectCourse(String[] array, int studentId) {
        List<EnrollRecord> enrollRecordList = new ArrayList<EnrollRecord>();
        EnrollRecord toGenerate;
        for (String item : array) {
            toGenerate = new EnrollRecord();
            toGenerate.setCourseId(Integer.parseInt(item));
            toGenerate.setStudentId(studentId);
            enrollRecordList.add(toGenerate);
        }
        return courseDAO.selectCourse(enrollRecordList);
    }

    public boolean dropCourse(String[] array, int studentId) {
        List<DropRecord> dropRecordList = new ArrayList<DropRecord>();
        DropRecord toGenerate;
        for (String item : array) {
            toGenerate = new DropRecord();
            toGenerate.setCourseId(Integer.parseInt(item));
            toGenerate.setStudentId(studentId);
            dropRecordList.add(toGenerate);
        }
        return courseDAO.dropCourse(dropRecordList);
    }

    public boolean addRequest(AddCourseRequest addCourseRequest) {
        return courseDAO.addRequest(addCourseRequest);
    }

    public boolean editRequest(EditCourseRequest editCourseRequest) {
        return courseDAO.editRequest(editCourseRequest);
    }

    public List<StudentGradesVO> getStudentGrades(int courseId) {
        return courseDAO.getStudentGrades(courseId);
    }
}
