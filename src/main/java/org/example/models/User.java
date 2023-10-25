package org.example.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
    private int id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 255, message = "name should be between 3 and 255")
    private String name;

    @Pattern(regexp = "\\d{4}\\.\\d{2}\\.\\d{2}", message = "Birthdate should be in format yyyy.mm.dd")
    private String birthDate;

    public User() {
    }

    public User(int id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
