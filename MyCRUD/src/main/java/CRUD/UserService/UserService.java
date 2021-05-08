package CRUD.UserService;

import CRUD.model.Role;
import CRUD.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void setUser(User user);
    void setRole(Role role);
    User getUser(long id);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    User getUserByLogin(String login);
}
