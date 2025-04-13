package web.DAO;

import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAllUser() {
        Query query = em.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(long id, User updateUser) {

        User user = em.find(User.class, id);
        if (user != null) {
            user.setName(updateUser.getName());
            user.setSurname(updateUser.getSurname());
            user.setEmail(updateUser.getEmail());
            em.merge(user);
        }
    }

    @Override
    public void deleteUser(long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(em.find(User.class, id));
        }

    }
}





