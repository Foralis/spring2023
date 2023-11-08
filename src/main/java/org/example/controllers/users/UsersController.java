package org.example.controllers.users;

import org.example.dao.OrderDao;
import org.example.dao.UserDao;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserDao userDao;
    private final OrderDao orderDao;

    @Autowired
    public UsersController(UserDao userDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
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
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "users/new";
        }
        userDao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userDao.showUser(id));
        model.addAttribute("books", orderDao.getAllBooksOrderedByUser(id));
        return "users/showUser";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.showUser(id));
        return "users/editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "users/editUser";
        }

        userDao.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userDao.deleteUser(id);
        return "redirect:/users";
    }
}
