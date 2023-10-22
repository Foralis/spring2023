package org.example.dao;

import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO user(name, birthDate) VALUES(?,?)", user.getName(),
                user.getBirthDate());
    }

    public User showUser(int id) {
        return jdbcTemplate.query("select * from user where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("There is no user with id = " + id));
    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE user set name = ?, birthDate = ? where id = ?", user.getName(),
                user.getBirthDate(), id);
    }
}
