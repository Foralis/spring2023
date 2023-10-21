package org.example.controllers.users;

import org.example.dao.UserDao;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDao userDao;

    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String getAllUsers(Model model){
        model.addAttribute("users", userDao.getAllUsers());
        return "users/users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("")
    public String createUser(@ModelAttribute("user") User user){
        return "redirect:/users/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDao.showUser(id));
        return "users/showUser";
    }
}
