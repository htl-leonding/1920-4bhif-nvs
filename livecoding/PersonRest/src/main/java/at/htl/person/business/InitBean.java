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
import java.time.LocalDate;
import java.time.Month;

@ApplicationScoped
public class InitBean {

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

    }
}
