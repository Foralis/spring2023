package org.example.dao;

import org.example.models.Book;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void order(int userId, int bookId) {
        jdbcTemplate.update("INSERT INTO orders(userId, bookId) VALUES(?,?)", userId, bookId);
    }

    public Book showBook(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("There is no book with id = " + id));
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book set author = ?, name = ?, established = ? where id = ?", book.getAuthor(),
                book.getName(), book.getEstablished(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }
}
