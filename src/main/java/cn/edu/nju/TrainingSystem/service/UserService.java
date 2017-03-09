package cn.edu.nju.TrainingSystem.service;

import cn.edu.nju.TrainingSystem.entity.User;

import java.util.List;

/**
 * Created by baiguofeng on 2017/3/8.
 */
public interface UserService {
    public void saveUsers(List<User> us);
    public List<User> getAllUsernames();
}