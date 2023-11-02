package org.example.controllers.users;

import org.example.dao.BookDao;
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

    @Autowired
    public BooksController(BookDao bookDao) {
        this.bookDao = bookDao;
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
}
