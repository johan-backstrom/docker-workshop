package com.github.johan.backstrom;

import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

@Component
public class PostgresClient {

    public List<Person> getPeople() {

        Sql2o sql2o = new Sql2o("postgresql://localhost:5432/postgres", "postgres", "postgres");
        String sql = "SELECT id, firstName, lastName FROM Person";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Person.class);
        }
    }
}