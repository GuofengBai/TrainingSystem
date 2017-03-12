package cn.edu.nju.TrainingSystem.vo;

/**
 * Created by baiguofeng on 2017/3/12.
 */
public class StudentCountVO {

    public String institutionName;
    public int institutionId;
    public int studentCount;

    public StudentCountVO(String institutionName, int institutionId, int studentCount) {
        this.institutionName = institutionName;
        this.institutionId = institutionId;
        this.studentCount = studentCount;
    }
}
