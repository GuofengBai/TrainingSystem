package cn.edu.nju.TrainingSystem.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by baiguofeng on 2017/3/10.
 */
public class StudentCoursePK implements Serializable {
    private int studentId;
    private int courseId;

    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Id
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
