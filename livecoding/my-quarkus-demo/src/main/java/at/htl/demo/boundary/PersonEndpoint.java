package at.htl.demo.boundary;

import at.htl.demo.control.PersonDao;
import at.htl.demo.model.Person;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonEndpoint {

    @Inject
    PersonDao personDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        Person person = personDao
                .find("name","Christiane")
                .firstResult();
        return Response
                .ok(person)
                .build();
    }

}
