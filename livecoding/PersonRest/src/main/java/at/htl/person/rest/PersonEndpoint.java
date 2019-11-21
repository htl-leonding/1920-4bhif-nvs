package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("person")
public class PersonEndpoint {

    public PersonEndpoint() {
    }

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Person> findAll() {
        return em
                .createNamedQuery("Person.findAll",Person.class)
                .getResultList();
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Person findByName(@QueryParam("name") String name) {
//        return em
//                .createNamedQuery("Person.findByName",Person.class)
//                .setParameter("NAME", name)
//                .getSingleResult();
//    }

    @GET
    @Path("demo")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response returnResponse() {
        List<Person> persons = em
                .createNamedQuery("Person.findAll",Person.class)
                .getResultList();
        return Response
                .status(Response.Status.OK)
                .header("chiara","christoph")
                .entity(persons)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createPerson(final @Context UriInfo uriInfo, Person person) {
        Person p = em.merge(person);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/" + p.getId())
                .build();
        return Response
                .created(uri)
                .header("operation", "object created")
                .build();
    }
}
