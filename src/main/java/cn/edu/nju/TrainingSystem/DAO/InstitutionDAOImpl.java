package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    public List<Institution> getList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Institution.class);
        return criteria.list();
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
        Object[] result = (Object[]) criteria.uniqueResult();
        if (Integer.parseInt(result[0] == null ? "" : result[0].toString()) == 0) {
            institution.setId(1000001);
        } else {
            institution.setId(1 + Integer.parseInt(result[1] == null ? "" : result[1].toString()));
        }
        institution.setBalance(0.0);
        sessionFactory.getCurrentSession().save(institution);
        return institution.getId();
    }

    public List<Course> getAllCourse(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Course where institutionId=:iid");
        query.setParameter("iid", id);
        return query.list();
    }

    public List<Course> getUnselectedCourse(int id, int sid) {
        String hql = "select c from Course c where c.institutionId=:iid and " +
                "c.id not in (select e.courseId from EnrollRecord e where e.studentId=:sid and e.droped=0)";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("iid", id);
        query.setParameter("sid", sid);
        return query.list();
    }

    public List<EnrollRecord> getEnrolled(int id) {
        String hql = "select e from EnrollRecord e,Course c where c.institutionId=:iid and e.courseId=c.id and e.droped=0";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("iid", id);
        return query.list();
    }

    public List<DropRecord> getDroped(int id) {
        String hql = "select d from DropRecord d,Course c where c.institutionId=:iid and d.courseId=c.id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("iid", id);
        return query.list();
    }

    public List<InstitutionPayment> getPayment(int id) {
        String hql = "from InstitutionPayment where institutionId=:iid";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("iid", id);
        return query.list();
    }

    public List<InstitutionRefund> getRefund(int id) {
        String hql = "from InstitutionRefund where institutionId=:iid";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("iid", id);
        return query.list();
    }

    public boolean uploadGrades(int courseId, int studentId, Double grades) {
        StudentCoursePK pk = new StudentCoursePK();
        pk.setCourseId(courseId);
        pk.setStudentId(studentId);
        EnrollRecord enrollRecord = (EnrollRecord) sessionFactory.getCurrentSession().get(EnrollRecord.class, pk);
        enrollRecord.setGrades(grades);
        sessionFactory.getCurrentSession().saveOrUpdate(enrollRecord);
        return true;
    }

    public List<StudentGradesVO> getStudentGrades(int institutionId) {
        String hql = "select s.name, s.id, c.name, c.id, e.grades from EnrollRecord e, Course c, Student s " +
                "where e.droped=0 and e.courseId=c.id and e.studentId=s.id and c.institutionId=:iid";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("iid", institutionId);
        List<Object[]> list = query.list();
        List<StudentGradesVO> result = new ArrayList<StudentGradesVO>();
        StudentGradesVO vo;
        for (Object[] objects : list) {
            vo = new StudentGradesVO((String) objects[0], Integer.parseInt(objects[1] == null ? "" : objects[1].toString()),
                    (String) objects[2], Integer.parseInt(objects[3] == null ? "" : objects[3].toString()), (Double) objects[4]);
            result.add(vo);
        }
        return result;
    }
}
