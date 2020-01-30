package at.htl.demo.control;

import at.htl.demo.model.Person;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.logging.Logger;

@ApplicationScoped
public class InitBean {

    @Inject
    PersonDao personDao;

    private static final Logger LOG = Logger.getLogger(InitBean.class.getSimpleName());

    @Transactional
    public void init(@Observes StartupEvent event) {
        LOG.info(InitBean.class.getCanonicalName() + "******************");

        Person chris = new Person("Christian", LocalDate.of(2000,12,17),"ledig");

        personDao.persist(chris);
    }

}
