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

    public void order(int userId, int bookId) {
        jdbcTemplate.update("INSERT INTO orders(userId, bookId) VALUES(?,?)", userId, bookId);
    }

    public boolean isBooked(int id) {
        int result = jdbcTemplate.queryForObject(
                "select count(1) from orders where bookId = ?",
                new Object[]{id},
                Integer.class);
        return result == 1;
    }
    


    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book set author = ?, name = ?, established = ? where id = ?", book.getAuthor(),
                book.getName(), book.getEstablished(), id);
    }

    public List<Book> getAllBooksOrderedByUser(int id){
        return jdbcTemplate.query("select * from library.book b where (select count(1) from library.orders where userId = ? and bookId = b.id) > 0",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void deleteOrder(int id) {
        jdbcTemplate.update("delete from orders where bookId = ?", id);
    }
}
