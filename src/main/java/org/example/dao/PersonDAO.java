package org.example.dao;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private List<Person> people;
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom",14, "tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob",52, "bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mik",18, "mik@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Kat",34, "kar@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        people.set(people.indexOf(show(id)), person);
    }
    public void delete(int id) {
        people.removeIf(i -> i.getId() == id);
    }
}