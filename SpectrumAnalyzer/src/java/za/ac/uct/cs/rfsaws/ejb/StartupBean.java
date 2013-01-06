/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author James
 */
@Startup
@Singleton
@DependsOn("TimerBean")
public class StartupBean {

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
        timerBean.setNewTimer(c.getTime(),"startup-timer");
    }

    /**
     * Any shut-down post operations.
     */
    @PreDestroy
    public void tearDown() {
    }
}
