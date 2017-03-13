package cn.edu.nju.TrainingSystem.vo;

/**
 * Created by baiguofeng on 2017/3/12.
 */
public class StudentCountVO {

    private String institutionName;
    private int institutionId;
    private int studentCount;

    public StudentCountVO(String institutionName, int institutionId, int studentCount) {
        this.institutionName = institutionName;
        this.institutionId = institutionId;
        this.studentCount = studentCount;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}
