package at.htl.readcsv.model;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String house;

    //region constructors
    public Person(String name, String city, String house) {
        this.name = name;
        this.city = city;
        this.house = house;
    }

    public Person() {
    }
    //endregion

    //region GEtter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
    //endregion

    @Override
    public String toString() {
        return String.format("%s, %s of %s", name, house, city);
    }
}
