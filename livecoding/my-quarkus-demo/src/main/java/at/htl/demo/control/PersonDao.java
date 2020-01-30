package at.htl.demo.control;

import at.htl.demo.model.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonDao implements PanacheRepository<Person> {
}
