package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/11.
 */
@Repository
public class InstitutionDAOImpl implements InstitutionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean login(String id, String password) {
        return login(Integer.parseInt(id), password);
    }

    public boolean login(int id, String password) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Institution.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("password", password));
        List<Student> list = criteria.list();
        if (list.size() == 1) {
            return true;
        }
        return false;
    }

    public Institution get(int id) {
        return (Institution) sessionFactory.getCurrentSession().get(Institution.class, id);
    }

    public boolean edit(Institution institution) {
        sessionFactory.getCurrentSession().saveOrUpdate(institution);
        return true;
    }

    public boolean delete(int id) {
        Institution institution = new Institution();
        institution.setId(id);
        sessionFactory.getCurrentSession().delete(institution);
        return false;
    }

    public Integer register(Institution institution) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Institution.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));
        criteria.setProjection(projectionList);
        Integer[] result = (Integer[]) criteria.uniqueResult();
        if (result[0] == 0) {
            institution.setId(1);
        } else {
            institution.setId(result[1] + 1);
        }
        sessionFactory.getCurrentSession().save(institution);
        return institution.getId();
    }

    public List<Course> getAllCourse(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Course where institutionId=?1");
        query.setParameter(1, id);
        return query.list();
    }

    public List<Course> getUnselectedCourse(int id, int sid) {
        String hql = "select c from Course c where c.institutionId=?1 and " +
                "c.id not in (select e.courseId from EnrollRecord e where e.studentId=?2 and e.droped=0)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        query.setParameter(2, sid);
        return query.list();
    }

    public List<EnrollRecord> getEnrolled(int id) {
        String hql = "select e from EnrollRecord e,Course c where c.institutionId=?1 and e.courseId=c.id and e.droped=0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        return query.list();
    }

    public List<DropRecord> getDroped(int id) {
        String hql = "select d from DropRecord d,Course c where c.institutionId=?1 and d.courseId=c.id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        return query.list();
    }

    public List<InstitutionPayment> getPayment(int id) {
        String hql = "from InstitutionPayment where institutionId=?1";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        return query.list();
    }

    public List<InstitutionRefund> getRefund(int id) {
        String hql = "from InstitutionRefund where institutionId=?1";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(1, id);
        return query.list();
    }

    public boolean oploadGrades(int courseId, int studentId, Double grades) {
        StudentCoursePK pk = new StudentCoursePK();
        pk.setCourseId(courseId);
        pk.setStudentId(studentId);
        EnrollRecord enrollRecord = (EnrollRecord) sessionFactory.getCurrentSession().get(EnrollRecord.class, pk);
        enrollRecord.setGrades(grades);
        sessionFactory.getCurrentSession().saveOrUpdate(enrollRecord);
        return true;
    }
}
