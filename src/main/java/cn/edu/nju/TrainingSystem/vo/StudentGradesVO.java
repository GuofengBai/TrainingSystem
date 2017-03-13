package cn.edu.nju.TrainingSystem.vo;

/**
 * Created by baiguofeng on 2017/3/12.
 */
public class StudentGradesVO {

    private String studentName;
    private int studentId;
    private String courseName;
    private int courseId;
    private Double grades;

    public StudentGradesVO(String studentName, int studentId, String courseName, int courseId, Double grades) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.courseId = courseId;
        this.grades = grades;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Double getGrades() {
        return grades;
    }

    public void setGrades(Double grades) {
        this.grades = grades;
    }
}
