/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author James
 */
@Singleton
public class AuctionScheduler {

    @Schedule(minute = "*", second = "*/15", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*")
    public void myTimer() {
        System.out.println("TICK");
    }
    
}
