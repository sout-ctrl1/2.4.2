package CRUD.controller;

import CRUD.UserService.UserService;
import CRUD.model.Role;
import CRUD.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final Set<Role> allRoles;

    public UserController(@Qualifier("userService") UserService userService, Set<Role> allRoles) {
        this.userService = userService;
        this.allRoles = allRoles;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping
    public String startPage() {
        return "header";
    }

    @GetMapping("/admin")
    public String listUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        Set<Role> roles = userService.getAllRoles();
        model.addAttribute("users", list);
        model.addAttribute("roles", roles);
        return "admin";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm(@PathVariable("id") long id, ModelMap model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        Set<Role> temp = new HashSet<>();
        user.getRoles().forEach(role -> temp.add(userService.getRoleByName(role.getName())));
        user.setRoles(temp);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping("/addUser")
    public String addUserForm(@ModelAttribute User user, ModelMap model) {
        model.addAttribute("allRoles", allRoles);
        return "addUser";
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @PostMapping("/addUser")
    public String addUser(User user) {
        Set<Role> temp = new HashSet<>();
        if (user.getRoles().isEmpty()) {
            user.addRole(new Role("USER"));
        }
        allRoles.stream().filter(role -> user.getRoles().contains(new Role(role.getName()))).forEach(temp::add);
        user.setRoles(temp);
        userService.setUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUser(Principal user, ModelMap modelMap) {
        User userBd = userService.getUserByLogin(user.getName());
        modelMap.addAttribute("user", userBd);
        return "/user";
    }

}
