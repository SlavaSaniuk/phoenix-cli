package by.bsac.services.accounting;

import by.bsac.models.Account;
import by.bsac.models.User;

import javax.transaction.Transactional;

public interface UserManager {

    @Transactional
    Account registerAccount(Account account);

    void deleteAccount(User a_user);
}
