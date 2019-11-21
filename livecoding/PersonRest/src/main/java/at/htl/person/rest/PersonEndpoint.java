package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.annotation.PostConstruct;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("person")
public class PersonEndpoint {

    public PersonEndpoint() {
    }

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML
    })
    public List<Person> findAll() {
        return em
                .createNamedQuery("Person.findAll", Person.class)
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
                .createNamedQuery("Person.findAll", Person.class)
                .getResultList();
        return Response
                .status(Response.Status.OK)
                .header("chiara", "christoph")
                .entity(persons)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createPerson(
            final @Context UriInfo uriInfo,
            JsonValue jsonValue) {

        if (jsonValue.getValueType() == JsonValue.ValueType.ARRAY) {
            JsonArray jsonArray = jsonValue.asJsonArray();
            for (JsonValue value : jsonArray) {
                System.out.println(value.toString());
                String name = value.asJsonObject().getString("name");
                System.out.println(name);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dob = LocalDate.parse(value.asJsonObject().getString("dob"), dtf);
                System.out.println(dob.toString());
                Person p = new Person(name, dob);
                p = em.merge(p);
            }
        } else {
            System.out.println("Ich bin ein Object");
        }


        return Response.ok().build();

//        Person p = em.merge(person);
//        URI uri = uriInfo.getAbsolutePathBuilder()
//                .path("/" + p.getId())
//                .build();
//        return Response
//                .created(uri)
//                .header("operation", "object created")
//                .build();
    }

    @POST
    @Path("x")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createPerson2(
            final @Context UriInfo uriInfo,
            Person person) {


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
