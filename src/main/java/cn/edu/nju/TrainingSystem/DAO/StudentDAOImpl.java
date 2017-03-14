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
        Object[] result = (Object[]) criteria.uniqueResult();
        if (Integer.parseInt(result[0] == null ? "" : result[0].toString()) == 0) {
            student.setId(1);
        } else {
            student.setId(Integer.parseInt(result[1] == null ? "" : result[1].toString()) + 1);
        }
        student.setBalance(1000.0);
        student.setLastChargeDate(new Date(System.currentTimeMillis()));
        student.setLevel(0);
        student.setPoint(0.0);
        student.setHistoryPoint(0.0);
        student.setState("激活");
        sessionFactory.getCurrentSession().save(student);
        return student.getId();
    }

    public List<Course> getEnrolled(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select c from Course c,EnrollRecord e where e.studentId=:sid and e.courseId=c.id and e.droped=0");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<Course> getDroped(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select c from Course c,DropRecord d where d.studentId=:sid and d.courseId=c.id");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<EnrollRecord> getEnrollRecord(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from EnrollRecord where studentId=:sid and droped=0");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<DropRecord> getDropRecord(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DropRecord where studentId=:sid");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<StudentPayment> getExpense(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentPayment where studentId=:sid and (state='完成' or state='交付')");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<StudentRefund> getRefend(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentRefund where studentId=:sid and state='交付'");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<StudentPayment> getUnfinishedExpense(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentPayment where studentId=:sid and state='未完成'");
        query.setParameter("sid", id);
        return query.list();
    }

    public List<StudentRefund> getUnfinishedRefund(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentRefund where studentId=:sid and state='未完成'");
        query.setParameter("sid", id);
        return query.list();
    }


    public boolean consume(String[] array) {
        int id;
        StudentPayment studentPayment;
        Student student;
        EnrollRecord enrollRecord;
        StudentCoursePK pk;
        Session session = sessionFactory.getCurrentSession();
        for (String item : array) {
            id = Integer.parseInt(item);
            studentPayment = (StudentPayment) session.get(StudentPayment.class, id);
            pk = new StudentCoursePK();
            pk.setStudentId(studentPayment.getStudentId());
            pk.setCourseId(studentPayment.getCourseId());
            enrollRecord = (EnrollRecord) session.get(EnrollRecord.class, pk);
            student = (Student) session.get(Student.class, studentPayment.getStudentId());
            if (studentPayment.getState().equals("未完成")) {
                studentPayment.setState("完成");
                student.outcome(studentPayment.getAmount());
                enrollRecord.setDroped((byte) 0);
                session.saveOrUpdate(studentPayment);
                session.saveOrUpdate(enrollRecord);
                session.saveOrUpdate(student);
            }
        }
        return true;
    }

    public boolean charge(int id, Double amount) {
        Student student = (Student) sessionFactory.getCurrentSession().get(Student.class, id);
        student.setBalance(student.getBalance() + amount);
        student.setLastChargeDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().saveOrUpdate(student);
        return true;
    }

    public boolean exchange(int id, Double amount) {
        Student student = (Student) sessionFactory.getCurrentSession().get(Student.class, id);
        student.exchange(amount);
        student.setLastChargeDate(new Date(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().saveOrUpdate(student);
        return true;
    }
}
