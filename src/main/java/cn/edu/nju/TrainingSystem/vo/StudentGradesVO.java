package cn.edu.nju.TrainingSystem.vo;

/**
 * Created by baiguofeng on 2017/3/12.
 */
public class StudentGradesVO {

    public String studentName;
    public int studentId;
    public String courseName;
    public int courseId;
    public Double grades;

    public StudentGradesVO(String studentName, int studentId, String courseName, int courseId, Double grades) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.courseId = courseId;
        this.grades = grades;
    }
}
