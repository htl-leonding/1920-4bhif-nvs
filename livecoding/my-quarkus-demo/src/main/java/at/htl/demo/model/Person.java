package at.htl.demo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name="MY_PERSON")
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