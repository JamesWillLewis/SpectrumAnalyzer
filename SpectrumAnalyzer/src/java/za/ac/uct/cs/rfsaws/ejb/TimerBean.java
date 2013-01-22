package za.ac.uct.cs.rfsaws.ejb;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 * Providers a timer service which handles all timed events,
 * such as auction scheduling.
 * 
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
@Singleton
public class TimerBean {

    /**
     * Timer service enterprise bean.
     */
    @Resource
    TimerService timerService;
    /**
     * Spectrum allocation injected bean.
     */
    @EJB
    SpectrumAllocationBean allocationBean;

    /**
     * Pushes a new timer into the timer service.
     * 
     * @param expireDate When the timer triggers a time-out event.
     * @param timerInfo A description/identifier of the timer.
     */
    public void pushNewTimer(Date expireDate, String timerInfo) {
        if (timerService != null) {
            TimerConfig config = new TimerConfig(timerInfo, true);
            Timer timer = timerService.createSingleActionTimer(expireDate, config);
            System.out.println("====[New timer set: " + timer.getInfo() + " - expires in " + timer.getTimeRemaining() + " ms]====");
        } else {
            System.err.println("TimerService resource not injected - unable to launch timer");
        }
    }

    /**
     * 
     * @param timer Timer which triggered the time-out.
     */
    @Timeout
    public void timeoutEvent(Timer timer) {
        System.out.println("====[Timer expired: " + timer.getInfo() + "]====");
        //scheduled method calls
        allocationBean.checkAuctions();
    }
}
