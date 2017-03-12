package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/11.
 */
public interface InstitutionDAO {

    boolean login(String id, String password);

    boolean login(int id, String password);

    Institution get(int id);

    boolean edit(Institution institution);

    boolean delete(int id);

    Integer register(Institution institution);

    List<Course> getAllCourse(int id);

    List<Course> getUnselectedCourse(int id, int sid);

    List<EnrollRecord> getEnrolled(int id);

    List<DropRecord> getDroped(int id);

    List<InstitutionPayment> getPayment(int id);

    List<InstitutionRefund> getRefund(int id);

    boolean oploadGrades(int courseId, int StudentId, Double grades);

}
