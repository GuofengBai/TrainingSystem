package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;

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

    List<EnrollRecord> getEnrollRecord(int id);

    List<DropRecord> getDropRecord(int id);

    List<StudentPayment> getExpense(int id);

    boolean consume(String[] array);

    List<StudentPayment> getUnfinishedExpense(int id);

    List<StudentRefund> getRefend(int id);

    List<StudentRefund> getUnfinishedRefund(int id);

    boolean charge(int id, Double amount);
}
