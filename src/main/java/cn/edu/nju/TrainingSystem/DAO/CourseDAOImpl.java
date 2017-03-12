package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/12.
 */
@Repository
public class CourseDAOImpl implements CourseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Course> getList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Course.class);
        return criteria.list();
    }

    public boolean selectCourse(List<EnrollRecord> enrollRecordList) {
        Session session = sessionFactory.getCurrentSession();
        StudentPayment studentPayment;
        Course toSelect;
        Student selecter;
        for (EnrollRecord item : enrollRecordList) {
            item.setDroped((byte) 1);
            toSelect = (Course) session.get(Course.class, item.getCourseId());
            selecter = (Student) session.get(Student.class, item.getStudentId());
            studentPayment = new StudentPayment();
            studentPayment.setState("未完成");
            studentPayment.setAmount(toSelect.getPrice() * selecter.discount());
            studentPayment.setCourseId(toSelect.getId());
            studentPayment.setInstitutionId(toSelect.getInstitutionId());
            studentPayment.setStudentId(selecter.getId());
            session.save(item);
            session.save(studentPayment);
        }
        return true;
    }

    public boolean dropCourse(List<DropRecord> dropRecordList) {
        Session session = sessionFactory.getCurrentSession();
        StudentCoursePK pk = new StudentCoursePK();
        EnrollRecord enrollRecord;
        StudentRefund studentRefund;
        Course toDrop;
        for (DropRecord item : dropRecordList) {
            pk.setCourseId(item.getCourseId());
            pk.setStudentId(item.getStudentId());
            enrollRecord = (EnrollRecord) session.get(EnrollRecord.class, pk);
            enrollRecord.setDroped((byte) 1);
            toDrop = (Course) session.get(Course.class, item.getCourseId());
            studentRefund = new StudentRefund();
            studentRefund.setStudentId(item.getStudentId());
            studentRefund.setCourseId(toDrop.getId());
            studentRefund.setInstitutionId(toDrop.getInstitutionId());
            studentRefund.setAmount(toDrop.getPrice() * 0.5);
            studentRefund.setState("未完成");
            session.saveOrUpdate(item);
            session.save(studentRefund);
            session.saveOrUpdate(enrollRecord);
        }
        return true;
    }

    public boolean addRequest(AddCourseRequest addCourseRequest) {
        addCourseRequest.setState("未完成");
        sessionFactory.getCurrentSession().saveOrUpdate(addCourseRequest);
        return true;
    }

    public boolean editRequest(EditCourseRequest editCourseRequest) {
        editCourseRequest.setState("未完成");
        sessionFactory.getCurrentSession().saveOrUpdate(editCourseRequest);
        return true;
    }
}
