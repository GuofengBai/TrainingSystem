package cn.edu.nju.TrainingSystem.entity;

import javax.persistence.*;

/**
 * Created by baiguofeng on 2017/3/10.
 */
@Entity
@Table(name="grades_record")
@IdClass(StudentCoursePK.class)
public class GradesRecord {
    private int studentId;
    private int courseId;
    private Double grades;

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
    public Double getGrades() {
        return grades;
    }

    public void setGrades(Double grades) {
        this.grades = grades;
    }
}
