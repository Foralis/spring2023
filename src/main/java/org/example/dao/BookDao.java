package org.example.dao;

import org.example.models.Book;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllBooks(){
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO user(name, birthDate) VALUES(?,?)", book.getName(),
                book.getEstablished());
    }

    public User showBook(int id) {
        return jdbcTemplate.query("select * from user where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("There is no user with id = " + id));
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE user set name = ?, birthDate = ? where id = ?", book.getName(),
                book.getEstablished(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from user where id = ?", id);
    }
}
