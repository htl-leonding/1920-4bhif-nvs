package at.htl.readcsv.model;

import javax.persistence.*;

@Entity
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String street;
    String city;
    int zipCode;

    @ManyToOne
    Person person;

}
