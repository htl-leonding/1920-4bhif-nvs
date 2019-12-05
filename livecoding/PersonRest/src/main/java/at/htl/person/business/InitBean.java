package at.htl.person.business;

import at.htl.person.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {

    private static final String FILE_NAME = "got.csv";

    public InitBean() {
    }

    @PersistenceContext
    EntityManager em;

    @Transactional
    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("Hello 4bhif!!");

        Person franz = new Person("Franz");
        em.persist(franz);
        em.persist(new Person("Susi", LocalDate.of(2001, Month.SEPTEMBER, 3)));
        em.persist(new Person("Mimi", LocalDate.of(2001, Month.MAY, 31)));

        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(FILE_NAME);
        try (Stream<String> stream = Files
                .lines(Paths
                        .get(url.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
