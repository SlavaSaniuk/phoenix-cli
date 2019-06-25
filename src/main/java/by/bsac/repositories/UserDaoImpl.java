package by.bsac.repositories;

import by.bsac.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void create(User a_user) {
        this.em.persist(a_user);
        this.em.flush();
    }

    @Override
    public User find(int a_user_id) {
       return this.em.find(User.class, a_user_id);
    }


    @Override
    @Transactional
    public void delete(User a_user) {
        if(!this.em.contains(a_user)) {
            User founded_user = this.find(a_user.getUserId());
            this.em.remove(founded_user);
        }else {
            this.em.remove(a_user);
        }
        this.em.flush();
    }

}
