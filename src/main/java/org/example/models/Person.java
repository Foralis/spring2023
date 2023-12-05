package org.example.models;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "name should be from 2 to 30")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "should be greater than 0")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    private List<Item> items;

   public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInsertStringTOSql() {
        return String.format("'%d', '%s', '%d'", this.id, this.name, this.age);
    }
}
