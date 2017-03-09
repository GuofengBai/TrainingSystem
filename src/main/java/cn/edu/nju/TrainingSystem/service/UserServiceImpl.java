package cn.edu.nju.TrainingSystem.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiguofeng on 2017/3/8.
 */
@Service
public class UserServiceImpl implements UserService {

    public List<String> getAllUsernames() {
        List<String> users = new ArrayList<String>();
        users.add("MarK");
        users.add("Ken");
        users.add("Fowafolo");
        return users;
    }

}
