package web.service;

import web.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();
    User getUser(long id);
     void saveUser(User user);
     void updateUser(long id, User updateUser);
     void deleteUser(long id);
}
