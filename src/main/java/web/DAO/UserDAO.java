package web.DAO;

import web.models.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUser();

    public User getUser(long id);

    public void saveUser(User user);

    public void updateUser(long id, User updateUser);

    public void deleteUser(long id);
}
