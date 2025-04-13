package web.service;

import org.springframework.stereotype.Service;
import web.DAO.UserDAO;
import web.models.User;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Transactional
    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(long id, User updateUser) {
        userDAO.updateUser(id, updateUser);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }
}
