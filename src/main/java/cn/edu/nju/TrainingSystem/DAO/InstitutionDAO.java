package cn.edu.nju.TrainingSystem.DAO;

import cn.edu.nju.TrainingSystem.entity.Institution;

/**
 * Created by baiguofeng on 2017/3/11.
 */
public interface InstitutionDAO {

    boolean login(String id, String password);

    boolean login(int id, String password);

    Institution get(int id);

    boolean edit(Institution institution);

    boolean delete(int id);

    Integer register(Institution institution);

}
