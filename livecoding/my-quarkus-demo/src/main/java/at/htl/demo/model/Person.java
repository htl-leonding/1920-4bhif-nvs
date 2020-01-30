package at.htl.demo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;
    public String status;

    public Person() {
    }

    public Person(String name, LocalDate birth, String status) {
        this.name = name;
        this.birth = birth;
        this.status = status;
    }
}