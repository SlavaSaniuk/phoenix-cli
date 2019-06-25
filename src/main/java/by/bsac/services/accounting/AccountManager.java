package by.bsac.services.accounting;

import by.bsac.models.User;

public interface AccountManager {

    boolean registerAccount(User a_user);

    void deleteAccount(User a_user);
}
