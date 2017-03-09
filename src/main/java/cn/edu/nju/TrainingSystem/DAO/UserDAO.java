package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.User;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/9.
 */
public interface UserDAO {
    public int save(User u);
    public List<User> findAll();
}
