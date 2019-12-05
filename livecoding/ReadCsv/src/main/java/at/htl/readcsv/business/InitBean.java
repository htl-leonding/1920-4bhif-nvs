package at.htl.readcsv.business;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class InitBean {

    private void init(
            @Observes
            @Initialized(ApplicationScoped.class) Object object) {
        System.out.println("Hello");
    }

}
