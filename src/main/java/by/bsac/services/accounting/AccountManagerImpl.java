package by.bsac.services.accounting;

import by.bsac.models.User;
import by.bsac.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountManagerImpl implements AccountManager {

    private UserDao user_dao;

    @Override
    public boolean registerAccount(User a_user) {
        user_dao.create(a_user);
        return true;
    }

    @Override
    public void deleteAccount(User a_user) {
        user_dao.delete(a_user);
    }

    @Autowired
    public void autowire(UserDao a_user_dao) {
        this.user_dao = a_user_dao;
    }

}
