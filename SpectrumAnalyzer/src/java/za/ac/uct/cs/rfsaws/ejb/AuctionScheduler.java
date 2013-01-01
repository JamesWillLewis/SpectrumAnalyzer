/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.Auction;

/**
 *
 * @author James
 */
@Singleton
public class AuctionScheduler {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;
    
    @EJB
    private Broker broker;

    @Schedule(minute = "*", second = "*/5", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*")
    /**
     * Periodic check for unresolved auction which have closed.
     */
    public void myTimer() {
        System.out.println("Checking for unresolved auctions...");
        resolve(findUnresolvedAuctions());
    }

    private List<Auction> findUnresolvedAuctions() {
        return em.createNamedQuery("findUnresolvedAuctions").getResultList();
    }

    private void resolve(List<Auction> auctions) {
        for (Auction a : auctions) {
            System.out.println("Resolving auction ID=" + a.getId());
            broker.resolveAuction(a);
        }
    }
}
