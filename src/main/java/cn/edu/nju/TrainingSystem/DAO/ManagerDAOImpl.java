package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.*;
import cn.edu.nju.TrainingSystem.vo.StudentCountVO;
import cn.edu.nju.TrainingSystem.vo.StudentGradesVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiguofeng on 2017/3/12.
 */
@Repository
public class ManagerDAOImpl implements ManagerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean approveAddRequest(String[] array) {
        int id;
        Course toAdd;
        AddCourseRequest request;
        Session session = sessionFactory.getCurrentSession();
        for (String item : array) {
            id = Integer.parseInt(item);
            request = (AddCourseRequest) session.get(AddCourseRequest.class, id);
            request.setState("完成");
            toAdd = new Course();
            toAdd.setInstitutionId(request.getInstitutionId());
            toAdd.setName(request.getName());
            toAdd.setTeacher(request.getTeacher());
            toAdd.setPrice(request.getPrice());
            toAdd.setStartTime(request.getStartTime());
            toAdd.setEndTime(request.getEndTime());
            session.saveOrUpdate(request);
            session.save(toAdd);
        }
        return true;
    }

    public boolean approveEditRequest(String[] array) {
        int id;
        Course toEdit;
        EditCourseRequest request;
        Session session = sessionFactory.getCurrentSession();
        for (String item : array) {
            id = Integer.parseInt(item);
            request = (EditCourseRequest) session.get(EditCourseRequest.class, id);
            request.setState("完成");
            toEdit = (Course) session.get(Course.class, request.getCourseId());
            toEdit.setInstitutionId(request.getInstitutionId());
            toEdit.setName(request.getName());
            toEdit.setTeacher(request.getTeacher());
            toEdit.setPrice(request.getPrice());
            toEdit.setStartTime(request.getStartTime());
            toEdit.setEndTime(request.getEndTime());
            session.saveOrUpdate(request);
            session.saveOrUpdate(toEdit);
        }
        return true;
    }

    public List<AddCourseRequest> getAddRequest() {
        Query query = sessionFactory.getCurrentSession().createQuery("from AddCourseRequest where state='未完成'");
        return query.list();
    }

    public List<EditCourseRequest> getEditRequest() {
        Query query = sessionFactory.getCurrentSession().createQuery("from EditCourseRequest where state='未完成'");
        return query.list();
    }

    public boolean approvePayment(String[] array) {
        int id;
        StudentPayment studentPayment;
        InstitutionPayment institutionPayment;
        Institution institution;
        Session session = sessionFactory.getCurrentSession();
        for (String item : array) {
            id = Integer.parseInt(item);
            studentPayment = (StudentPayment) session.get(StudentPayment.class, id);
            institution = (Institution) session.get(Institution.class, studentPayment.getInstitutionId());
            if (studentPayment.getState().equals("完成")) {
                studentPayment.setState("交付");
                institutionPayment = new InstitutionPayment();
                institutionPayment.setStudentId(studentPayment.getStudentId());
                institutionPayment.setCourseId(studentPayment.getCourseId());
                institutionPayment.setInstitutionId(studentPayment.getInstitutionId());
                institutionPayment.setAmount(studentPayment.getAmount());
                institutionPayment.setState("交付");
                institution.setBalance(institution.getBalance() + institutionPayment.getAmount());
                session.saveOrUpdate(studentPayment);
                session.save(institutionPayment);
                session.saveOrUpdate(institution);
            }
        }
        return true;
    }

    public boolean approveRefund(String[] array) {
        int id;
        StudentRefund studentRefund;
        InstitutionRefund institutionRefund;
        Institution institution;
        Student student;
        Session session = sessionFactory.getCurrentSession();
        for (String item : array) {
            id = Integer.parseInt(item);
            studentRefund = (StudentRefund) session.get(StudentRefund.class, id);
            institution = (Institution) session.get(Institution.class, studentRefund.getInstitutionId());
            student = (Student) session.get(Student.class, studentRefund.getStudentId());
            if (studentRefund.getState().equals("未完成")) {
                studentRefund.setState("交付");
                institutionRefund = new InstitutionRefund();
                institutionRefund.setStudentId(studentRefund.getStudentId());
                institutionRefund.setCourseId(studentRefund.getCourseId());
                institutionRefund.setInstitutionId(studentRefund.getInstitutionId());
                institutionRefund.setAmount(studentRefund.getAmount());
                institutionRefund.setState("交付");
                institution.setBalance(institution.getBalance() - institutionRefund.getAmount());
                student.income(studentRefund.getAmount());
                session.saveOrUpdate(studentRefund);
                session.save(institutionRefund);
                session.saveOrUpdate(institution);
                session.saveOrUpdate(student);
            }
        }
        return true;
    }

    public List<StudentPayment> getUnfinishedPayment() {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentPayment where state='完成'");
        return query.list();
    }

    public List<StudentRefund> getUnfinishedRefund() {
        Query query = sessionFactory.getCurrentSession().createQuery("from StudentRefund where state='未完成'");
        return query.list();
    }

    public List<StudentCountVO> getStudentCount() {
        String hql = "select i.name,i.id,count(*) from EnrollRecord e, Course c, Institution i " +
                "where e.droped=0 and e.courseId=c.id and c.institutionId=i.id group by i.name,i.id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Object[]> list = query.list();
        List<StudentCountVO> result = new ArrayList<StudentCountVO>();
        StudentCountVO vo;
        for (Object[] objects : list) {
            vo = new StudentCountVO((String) objects[0], Integer.parseInt(objects[1] == null ? "" : objects[1].toString()),
                    Integer.parseInt(objects[2] == null ? "" : objects[2].toString()));
            result.add(vo);
        }
        return result;
    }

    public List<StudentGradesVO> getStudentGrades() {
        String hql = "select s.name, s.id, c.name, c.id, e.grades from EnrollRecord e, Course c, Student s " +
                "where e.droped=0 and e.courseId=c.id and e.studentId=s.id";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
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
