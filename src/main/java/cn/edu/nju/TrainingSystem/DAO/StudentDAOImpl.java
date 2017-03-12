package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by baiguofeng on 2017/3/10.
 */
@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean login(String id, String password) {
        return login(Integer.parseInt(id), password);
    }

    public boolean login(int id, String password) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("password", password));
        List<Student> list = criteria.list();
        if (list.size() == 1) {
            return true;
        }
        return false;
    }

    public Student find(int id) {
        return (Student) sessionFactory.getCurrentSession().get(Student.class, id);
    }

    public boolean edit(Student student) {
        sessionFactory.getCurrentSession().saveOrUpdate(student);
        return true;
    }

    public boolean delete(int id) {
        Student student = new Student();
        student.setId(id);
        sessionFactory.getCurrentSession().delete(student);
        return true;
    }

    public Integer register(Student student) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));
        criteria.setProjection(projectionList);
        Integer[] result = (Integer[]) criteria.uniqueResult();
        if (result[0] == 0) {
            student.setId(1);
        } else {
            student.setId(result[1] + 1);
        }
        student.setLastChargeDate(new Date(System.currentTimeMillis()));
        student.setLevel(0);
        student.setPoint(0.0);
        student.setState("激活");
        sessionFactory.getCurrentSession().save(student);
        return student.getId();
    }

    public List<Course> getEnrolled(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select c from Course c,EnrollRecord e where e.studentId=?1 and e.courseId=c.id and e.droped=0");
        query.setParameter(1, id);
        return query.list();
    }

    public List<Course> getDroped(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select c from Course c,DropRecord d where d.studentId=?1 and d.courseId=c.id");
        query.setParameter(1, id);
        return query.list();
    }

    public List<EnrollRecord> getEnrollRecord(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from EnrollRecord where studentId=?1 and droped=0");
        query.setParameter(1, id);
        return query.list();
    }

    public List<DropRecord> getDropRecord(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DropRecord where studentId=?1");
        query.setParameter(1, id);
        return query.list();
    }

    public List<StudentPayment> getExpense(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentPayment where studentId=?1 and state='完成'");
        query.setParameter(1, id);
        return query.list();
    }

    public List<StudentRefund> getRefend(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentRefund where studentId=?1 and state='完成'");
        query.setParameter(1, id);
        return query.list();
    }

    public List<StudentPayment> getUnfinishedExpense(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentPayment where studentId=?1 and state='未完成'");
        query.setParameter(1, id);
        return query.list();
    }

    public List<StudentRefund> getUnfinishedRefund(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentRefund where studentId=?1 and state='未完成'");
        query.setParameter(1, id);
        return query.list();
    }

    public boolean consume(List<StudentPayment> paymentList) {
        Session session = sessionFactory.getCurrentSession();
        Student student;
        EnrollRecord enrollRecord;
        StudentCoursePK pk = new StudentCoursePK();
        for (StudentPayment studentPayment : paymentList) {
            if (studentPayment.getState().equals("未完成")) {
                studentPayment.setState("完成");
                student = (Student) session.get(Student.class, studentPayment.getStudentId());
                student.outcome(studentPayment.getAmount());
                pk.setStudentId(student.getId());
                pk.setCourseId(studentPayment.getCourseId());
                enrollRecord = (EnrollRecord) session.get(EnrollRecord.class, pk);
                enrollRecord.setDroped((byte) 0);
                session.saveOrUpdate(studentPayment);
                session.saveOrUpdate(student);
                session.saveOrUpdate(enrollRecord);
            }
        }
        return true;
    }
}
