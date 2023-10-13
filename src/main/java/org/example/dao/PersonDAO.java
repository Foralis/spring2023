package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        //return jdbcTemplate.query("select * from person", new PersonMapper());
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
//        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String email) {
//        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);
        return jdbcTemplate.query("select * from person where email = ?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?,?,?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? where id=?",
                person.getName(), person.getAge(), person.getEmail(), person.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("Delete from person where id=?", id);
    }


    //////////////
    // Тестирууем производительность пакетной вставки
    //////////////


    public void testMultipleUpdate() {
        List<Person> people = create1000people();
        long before = System.currentTimeMillis();

        for (Person person : people
        ) {
            save(person);
        }

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (before - after));
    }

    private List<Person> create1000people() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i, 30, String.format("test%d@mail.ru", i)));
        }

        return people;
    }

    public void testBatchUpdate() {
        List<Person> people = create1000people();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1, people.get(i).getId());
                        preparedStatement.setString(2, people.get(i).getName());
                        preparedStatement.setInt(3, people.get(i).getAge());
                        preparedStatement.setString(4, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (before - after));
    }



}
