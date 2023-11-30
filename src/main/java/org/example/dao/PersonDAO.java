package org.example.dao;

import org.example.models.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        //return jdbcTemplate.query("select * from person", new PersonMapper());
        //return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select  p from Person p", Person.class).getResultList();
        return people;
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
//        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);
//        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
        Session session = sessionFactory.getCurrentSession();
//        Person person = (Person) session
//                .createQuery("select  p from Person p where id = :id")
//                .setParameter("id", id)
//                .uniqueResult();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Transactional
    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)", person.getName(), person.getAge(), person.getEmail());
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? where id=?",
//                person.getName(), person.getAge(), person.getEmail(), person.getId());
        Session session = sessionFactory.getCurrentSession();
        //session.merge(person);
        session.update(person);
    }

    @Transactional
    public void delete(int id) {
//        jdbcTemplate.update("Delete from person where id=?", id);
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);

    }
}
