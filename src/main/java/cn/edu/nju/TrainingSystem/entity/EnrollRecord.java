package cn.edu.nju.TrainingSystem.entity;

import javax.persistence.*;

/**
 * Created by baiguofeng on 2017/3/10.
 */
@Entity
@Table(name = "enroll_record")
@IdClass(StudentCoursePK.class)
public class EnrollRecord {
    private int studentId;
    private int courseId;
    private byte droped;

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

    @Basic
    public byte getDroped() {
        return droped;
    }

    public void setDroped(byte droped) {
        this.droped = droped;
    }
}
