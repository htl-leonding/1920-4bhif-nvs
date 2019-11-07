package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person findByName(@QueryParam("name") String name) {
        return em
                .createNamedQuery("Person.findByName",Person.class)
                .setParameter("NAME", name)
                .getSingleResult();
    }

    @GET
    @Path("demo")
    public Response returnResponse() {
        return Response.accepted().build();
    }
}
