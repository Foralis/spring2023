package org.example.controllers.users;

import org.example.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final PersonDao personDao;

    @Autowired
    public UsersController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String getAllUsers(Model model){
        model.addAttribute("users", personDao.getAllUsers());
        return "users/users";
    }
}
