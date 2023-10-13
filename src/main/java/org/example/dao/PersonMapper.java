package org.example.dao;

import org.example.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Person(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getString("address"));
    }
}
