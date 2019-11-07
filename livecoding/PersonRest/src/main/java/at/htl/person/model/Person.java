package at.htl.person.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Person {

    @Transient
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;

    //region Constructors
    public Person() {
    }

    public Person(String name) {
        this.name = name;
        dob = LocalDate.now();
    }

    public Person(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }
    //endregion

    //region getter and setter
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    //endregion


    @Override
    public String toString() {
        return String.format("%d: %s (%s)", id, name, dob.format(dtf));
    }
}
