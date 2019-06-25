package by.bsac.repositories;

import by.bsac.models.User;

public interface UserDao {

    void create(User a_user);
    User find(int a_user_id);
    void delete(User a_user);

}
