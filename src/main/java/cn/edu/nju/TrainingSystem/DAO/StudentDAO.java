package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.Course;
import cn.edu.nju.TrainingSystem.entity.Student;
import cn.edu.nju.TrainingSystem.entity.StudentPayment;
import cn.edu.nju.TrainingSystem.entity.StudentRefund;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/10.
 */
public interface StudentDAO {

    boolean login(String id, String password);

    boolean login(int id, String password);

    Student find(int id);

    boolean edit(Student student);

    boolean delete(int id);

    Integer register(Student student);

    List<Course> getEnrolled(int id);

    List<Course> getDroped(int id);

    List<StudentPayment> getExpense(int id);

    boolean consume(List<StudentPayment> paymentList);

    List<StudentPayment> getUnfinishedExpense(int id);

    List<StudentRefund> getRefend(int id);

    List<StudentRefund> getUnfinishedRefund(int id);
}
