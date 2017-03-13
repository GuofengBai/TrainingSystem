package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.entity.*;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
public interface StudentService {

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

    List<StudentRefund> getRefend(int id);

    List<StudentPayment> getUnfinishedExpense(int id);

    List<StudentRefund> getUnfinishedRefund(int id);

    boolean consume(String[] array);

    boolean charge(int id, Double amount);

}
