package org.example.dao;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(1, "Tom"));
        people.add(new Person(2, "Bob"));
        people.add(new Person(3, "Mike"));
        people.add(new Person(4, "Katy"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}
