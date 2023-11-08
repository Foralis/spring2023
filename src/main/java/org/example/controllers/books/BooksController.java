package org.example.controllers.books;

import org.example.dao.BookDao;
import org.example.dao.OrderDao;
import org.example.dao.UserDao;
import org.example.models.Book;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDao bookDao;
    private final UserDao userDao;
    private final OrderDao orderDao;

    @Autowired
    public BooksController(BookDao bookDao,
                           UserDao userDao,
                           OrderDao orderDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    @GetMapping()
    public String getAllBooks(Model model){
        model.addAttribute("books", bookDao.getAllBooks());
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
        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDao.showBook(id));
        if(orderDao.isBooked(id)) {
            model.addAttribute("isOrdered", true);
            model.addAttribute("user", userDao.showUserWhoOrderedBook(id));
        } else {
            model.addAttribute("users", userDao.getAllUsers());
            model.addAttribute("isOrdered", false);
            model.addAttribute("user", new User());
        }
        return "books/showBook";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.showBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "books/editBook";
        }

        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDao.deleteBook(id);
        return "redirect:/books";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderDao.deleteOrder(id);
        return "redirect:/books";
    }

    @PostMapping("/order/{id}") String orderBook(@PathVariable("id") int id, @ModelAttribute("user") User user){
        orderDao.order(user.getId(), id);
        return "redirect:/books";
    }
}
