package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.Institution;
import cn.edu.nju.TrainingSystem.entity.Student;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/11.
 */
public class InstitutionDAOImpl implements InstitutionDAO {

    @Autowired
    SessionFactory sessionFactory;

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
}
