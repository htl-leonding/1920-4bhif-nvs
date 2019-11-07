package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("person")
public class PersonEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> findAll() {
        return em
                .createNamedQuery("Person.findAll",Person.class)
                .getResultList();
    }
}
