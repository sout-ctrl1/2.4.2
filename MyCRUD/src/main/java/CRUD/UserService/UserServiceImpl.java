package CRUD.UserService;

import CRUD.Dao.UserDao;
import CRUD.model.Role;
import CRUD.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(@Qualifier("userDao") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setUser(User user) {
        userDao.setUser(user);
    }

    @Override
    public void setRole(Role role) {
        userDao.setRole(role);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id).orElse(null);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Set<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login).orElse(null);
    }
}
