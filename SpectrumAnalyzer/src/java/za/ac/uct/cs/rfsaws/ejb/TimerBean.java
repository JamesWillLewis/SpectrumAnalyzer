/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author James
 */
@Singleton
public class TimerBean {

    @Resource
    TimerService timerService;

    @EJB
    SpectrumAllocationBean allocationBean;

    public void setNewTimer(Date expireDate, String timerInfo) {
        if (timerService != null) {
            TimerConfig config = new TimerConfig(timerInfo, true);
            Timer timer = timerService.createSingleActionTimer(expireDate, config);
            System.out.println("====[New timer set: "+timer.getInfo()+" - expires in " + timer.getTimeRemaining() + " ms]====");
        } else{
            System.err.println("TimerService not injected - unable to launch timer");
        }
    }

    @Timeout
    public void timeout(Timer timer) {
        System.out.println("====[Timer expired: "+ timer.getInfo()+"]====");
        allocationBean.checkAuctions();
    }

}
