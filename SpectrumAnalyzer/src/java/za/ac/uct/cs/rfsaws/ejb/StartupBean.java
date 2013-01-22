package za.ac.uct.cs.rfsaws.ejb;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Singleton session bean, with eager loading, launched on application
 * initialization to submit a "startup timer", and perform any pre-execution
 * operations.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Startup
@Singleton
@DependsOn("TimerBean")
public class StartupBean {

    /**
     * Injected timer bean.
     */
    @EJB
    TimerBean timerBean;

    /**
     * Any start-up initialization operations.
     */
    @PostConstruct
    public void setUp() {
        /*
         * Start timer to resolve any unresolved auctions on runtime which
         * might be missed if the auction's timer wasn't persisted correctly.
         */
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 1);
        System.out.println("====[Launching initial timeout - expires in 1 seconds===");
        timerBean.pushNewTimer(c.getTime(), "startup-timer");
    }

    /**
     * Any shut-down post operations.
     */
    @PreDestroy
    public void tearDown() {
    }
}
