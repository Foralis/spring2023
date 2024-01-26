package org.example.controllers.books;


import org.example.models.Book;
import org.example.models.User;
import org.example.services.BooksService;
import org.example.services.OrdersService;
import org.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {


    private final UsersService usersService;
    private final BooksService booksService;
    private final OrdersService ordersService;

    @Autowired
    public BooksController(UsersService usersService, BooksService booksService, OrdersService ordersService) {
        this.usersService = usersService;
        this.booksService = booksService;
        this.ordersService = ordersService;
    }

    @GetMapping()
    public String getAllBooks(Model model){
        model.addAttribute("books", booksService.findAll());
        return "books/books";
    }

    @GetMapping("/new")
    public String newUserForm(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping("")
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.showBook(id));
        if(ordersService.isBooked(id)) {
            model.addAttribute("isOrdered", true);
            model.addAttribute("user", usersService.showUserWhoOrderedBook(id));
        } else {
            model.addAttribute("users", usersService.findAll());
            model.addAttribute("isOrdered", false);
            model.addAttribute("user", new User());
        }
        return "books/showBook";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.showBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "books/editBook";
        }

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        booksService.deleteBook(id);
        return "redirect:/books";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        ordersService.deleteOrderByBookId(id);
        return "redirect:/books";
    }

    @PostMapping("/order/{id}") String orderBook(@PathVariable("id") int id, @ModelAttribute("user") User user){
        ordersService.order(user, id);
        return "redirect:/books";
    }
}
