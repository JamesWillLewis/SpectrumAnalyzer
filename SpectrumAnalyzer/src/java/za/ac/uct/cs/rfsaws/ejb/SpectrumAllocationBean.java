/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.ejb;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.uct.cs.rfsaws.auction.KnapsackAlgorithm;
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
        System.out.println("====[" + bids.size() + " bids found for auction id=" + a.getId() + "]====");
        double capacity = a.getAllocation().getBandFreqUpper() - a.getAllocation().getBandFreqLower();

        List<Bid> winningBids = new LinkedList<Bid>();
        if (bids.size() > 0) {
            KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm(bids, capacity, a.getId());
            winningBids = knapsackAlgorithm.bestFirstBranchAndBound();
        } else {
            System.out.println("====[Auction id=" + a.getId() + " had no bids - terminated]====");
        }

        a.setResolved((byte) 1);
        em.persist(a);
        System.out.println("====[Auction id=" + a.getId() + " has been resolved]====");

        List<Lease> leases = new LinkedList<Lease>();
        double spectrumLowerBound = a.getAllocation().getBandFreqLower();

        Collections.shuffle(bids);

        for (Bid b : winningBids) {
            Lease l = new Lease();
            l.setAllocation(a.getAllocation());
            l.setBandFreqLower(spectrumLowerBound);
            spectrumLowerBound += b.getSegment().getBandWidth();
            l.setBandFreqUpper(spectrumLowerBound);
            l.setDateEnd(a.getAllocation().getEndDate());
            l.setDateStart(a.getAllocation().getBeginDate());
            l.setNode(b.getBidder());
            l.setSegment(b.getSegment());
            leases.add(l);
        }
        System.out.println("====[" + leases.size() + " new leases allocated]====");

        return leases;
    }
}
