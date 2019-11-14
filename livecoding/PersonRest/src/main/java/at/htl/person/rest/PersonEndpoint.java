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

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    public Response returnResponse() {
        return Response.accepted().build();
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
