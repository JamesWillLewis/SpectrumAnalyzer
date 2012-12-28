/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import za.ac.uct.cs.rfsaws.entities.Auction;

/**
 *
 * @author James
 */
@Singleton
public class AuctionScheduler {

    @EJB
    private AuctionFacade auctionFacade;
    
    @EJB
    private Broker broker;

    @Schedule(minute = "*", second = "*/15", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*")
    public void myTimer() {
        resolve(findUnresolvedAuctions());
    }

    private List<Auction> findUnresolvedAuctions() {
        return auctionFacade.getEntityManager().createNamedQuery("findUnresolvedAuctions").getResultList();
    }
    
    private void resolve(List<Auction> auctions){
        for(Auction a: auctions){
            System.out.println(a);
        }
    }
}
