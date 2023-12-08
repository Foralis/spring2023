package org.example.controllers;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.example.services.ItemsService;
import org.example.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;
    private PeopleService peopleService;

    private ItemsService itemsService;


    public PeopleController(PersonDAO personDAO, PeopleService peopleService, ItemsService itemsService) {
        this.personDAO = personDAO;
        this.peopleService = peopleService;
        this.itemsService = itemsService;
    }

    @GetMapping()
    public String index(Model model){
        // получим всех людей из ДАО и передадим на отображение
       // model.addAttribute("people", peopleService.findAll());
//        itemsService.findByItemName("lamp");
//        itemsService.findByOwner(peopleService.findAll().get(0));
personDAO.testNPlus1();
        //peopleService.test();

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // получим одно человека по айди из ДАО и передадим на отображение
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
