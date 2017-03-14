package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.DAO.StudentDAO;
import cn.edu.nju.TrainingSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/13.
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public boolean login(String id, String password) {
        return studentDAO.login(id, password);
    }

    public boolean login(int id, String password) {
        return studentDAO.login(id, password);
    }

    public Student find(int id) {
        return studentDAO.find(id);
    }

    public boolean edit(Student student) {
        return studentDAO.edit(student);
    }

    public boolean delete(int id) {
        return studentDAO.delete(id);
    }

    public Integer register(Student student) {
        return studentDAO.register(student);
    }

    public List<Course> getEnrolled(int id) {
        return studentDAO.getEnrolled(id);
    }

    public List<Course> getDroped(int id) {
        return studentDAO.getDroped(id);
    }

    public List<EnrollRecord> getEnrollRecord(int id) {
        return studentDAO.getEnrollRecord(id);
    }

    public List<DropRecord> getDropRecord(int id) {
        return studentDAO.getDropRecord(id);
    }

    public List<StudentPayment> getExpense(int id) {
        return studentDAO.getExpense(id);
    }

    public List<StudentRefund> getRefend(int id) {
        return studentDAO.getRefend(id);
    }

    public List<StudentPayment> getUnfinishedExpense(int id) {
        return studentDAO.getUnfinishedExpense(id);
    }

    public List<StudentRefund> getUnfinishedRefund(int id) {
        return studentDAO.getUnfinishedRefund(id);
    }

    public boolean consume(String[] array) {
        return studentDAO.consume(array);
    }

    public boolean charge(int id, Double amount) {
        return studentDAO.charge(id, amount);
    }

    public boolean exchange(int id, Double amount) {
        return studentDAO.exchange(id, amount);
    }
}
