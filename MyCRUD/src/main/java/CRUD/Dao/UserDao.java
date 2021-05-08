package CRUD.Dao;

import CRUD.model.Role;
import CRUD.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDao {
    void setUser(User user);
    void setRole(Role role);
    Optional<User> getUser(long id);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    Optional<User> getUserByLogin(String login);
}
