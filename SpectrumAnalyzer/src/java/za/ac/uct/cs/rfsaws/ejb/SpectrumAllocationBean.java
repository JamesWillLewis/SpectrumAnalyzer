/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.entities.Auction;
import za.ac.uct.cs.rfsaws.entities.Bid;
import za.ac.uct.cs.rfsaws.entities.Lease;

/**
 *
 * @author James
 */
@Stateful
public class SpectrumAllocationBean {

    @PersistenceContext(unitName = "SpectrumAnalyzerPU")
    private EntityManager em;
    

    public List<Lease> allocate(Auction a) {
       List<Bid> bids = em.createNamedQuery("findBidsOfAuction").setParameter("a", a).getResultList();
       double capacity = a.getAuctionedSpectrum().getBandFreqUpper() - a.getAuctionedSpectrum().getBandFreqLower();


        return null;
    }


   
}
