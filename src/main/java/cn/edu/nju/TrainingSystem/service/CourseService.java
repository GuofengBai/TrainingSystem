package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.entity.AddCourseRequest;
import cn.edu.nju.TrainingSystem.entity.Course;
import cn.edu.nju.TrainingSystem.entity.EditCourseRequest;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
public interface CourseService {

    List<Course> getList();

    List<Course> getUnselectedList(int studentId);

    boolean selectCourse(String[] array, int studentId);

    boolean dropCourse(String[] array, int studentId);

    boolean addRequest(AddCourseRequest addCourseRequest);

    boolean editRequest(EditCourseRequest editCourseRequest);

}
