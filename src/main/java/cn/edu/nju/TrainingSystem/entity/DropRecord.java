package cn.edu.nju.TrainingSystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by baiguofeng on 2017/3/10.
 */
@Entity
@Table(name = "drop_record")
@IdClass(StudentCoursePK.class)
public class DropRecord {
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
