package by.bsac.services.accounting;

import by.bsac.models.Account;
import by.bsac.models.User;
import by.bsac.repositories.AccountRepository;
import by.bsac.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;


public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository u_repository;

    @Autowired
    private AccountRepository a_repository;

    @Override
    @Transactional
    public Account registerAccount(Account account) {

        User user = new User();
        user = u_repository.save(user);
        account.setAccount_email("123xfcd");
        account.setAccount_password("sdsd");

        account.setAccount_owner(user);
        user.setUser_account(account);

        this.a_repository.saveAndFlush(account);

        return account;
    }

    @Override
    public void deleteAccount(User a_user) {

    }


}
