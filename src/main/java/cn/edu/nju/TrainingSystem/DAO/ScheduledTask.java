package cn.edu.nju.TrainingSystem.DAO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by baiguofeng on 2017/3/11.
 */
@Component
public class ScheduledTask {

    @Autowired
    SessionFactory sessionFactory;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void update() {
        System.out.println("执行更新任务.....");
        String sql = "update student set state ='暂停' where state='激活' and balance<1000 " +
                "and YEAR(CURDATE())-YEAR(s.lastChargeDate)>=1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
        sql = "delete student where state='暂停' and YEAR(CURDATE())-YEAR(lastChargeDate)>=2";
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }
}
